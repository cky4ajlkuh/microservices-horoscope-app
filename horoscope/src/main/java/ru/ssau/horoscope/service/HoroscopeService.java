package ru.ssau.horoscope.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ssau.horoscope.dto.HoroscopeResponse;
import ru.ssau.horoscope.dto.HoroscopeRequest;
import ru.ssau.horoscope.exception.RequestException;
import ru.ssau.horoscope.api.HoroscopeApi;
import ru.ssau.horoscope.util.Zodiac;

@Service
@RequiredArgsConstructor
public class HoroscopeService {

    @Autowired
    private final HoroscopeApi horoscopeApi;


    public HoroscopeResponse getHoroscope(HoroscopeRequest request) {
        if (request.getDay().isEmpty() || request.getSign() == 0 || request.getSign() > 12) {
            throw new RequestException("Тело запроса содержит некорректные данные");
        }
        String zodiac = Zodiac.values()[request.getSign()].toString();
        return horoscopeApi.getDailyHoroscope(zodiac, request.getDay());
    }

}