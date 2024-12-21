package ru.ssau.horoscope.api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.ssau.horoscope.dto.HoroscopeResponse;
import ru.ssau.horoscope.exception.ExternalApiException;

@Service
@RequiredArgsConstructor
public class HoroscopeApi {

    @Value("${url.horoscope}")
    private String BASE_URL;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${spring.kafka.topic.name}")
    private String topic;

    private final KafkaTemplate<String, String> kafkaTemplate;

    public HoroscopeResponse getDailyHoroscope(String zodiac, String day) {
        HoroscopeResponse response = new HoroscopeResponse();
        try {
            kafkaTemplate.send(topic,  "Получен запрос на гороскоп для " + zodiac + " на " + day);
            String url = BASE_URL + "daily?sign=" + zodiac + "&day=" + day;
            ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class);
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(entity.getBody(), JsonObject.class);
            if (jsonObject.getAsJsonObject("data").has("horoscope_data")) {
                response.setHoroscope(jsonObject.getAsJsonObject("data").get("horoscope_data").getAsString());
                kafkaTemplate.send(topic,  "Получен гороскоп с horoscope-api");
                return response;
            } else {
                throw new ExternalApiException("Ошибка! Данные с horoscope app api не получены");
            }
        } catch (Exception exception) {
            response.setError(exception.getMessage());
            return response;
        }
    }
}
