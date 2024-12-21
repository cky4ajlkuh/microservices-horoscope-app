package ru.ssau.horoscope.api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.ssau.horoscope.exception.ExternalApiException;

@Service
public class TranslateApi {

    @Autowired
    private RestTemplate restTemplate;

    public String translate(String text) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String body = "{" +
                "\"q\": \"" + text + "\"," +
                "\"source\":\"en\"," +
                "\"target\":\"ru\""+
                "}";

        String url = "https://libretranslate.com/translate";
        HttpEntity<String> entity = new HttpEntity<>(body, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(response.getBody(), JsonObject.class);
        if (jsonObject.has("translatedText")) {
            return jsonObject.get("translatedText").toString();
        } else {
            throw new ExternalApiException("Ошибка! Данные с libre translate не получены");
        }
    }

}
