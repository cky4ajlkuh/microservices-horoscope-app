package ru.ssau.horoscope.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.ApplicationScope;
import ru.ssau.horoscope.dto.HoroscopeRequest;
import ru.ssau.horoscope.dto.HoroscopeResponse;
import ru.ssau.horoscope.service.HoroscopeService;

@RestController
@ApplicationScope
@CrossOrigin
public class HoroscopeController {

    @Autowired
    private HoroscopeService service;

    @PostMapping(value = "/getHoroscope")
    public HoroscopeResponse getDailyHoroscopeData(@RequestBody HoroscopeRequest request) {
        if (request == null) {
            throw new RuntimeException("На сервер пришел пустой запрос!");
        }
        return service.getHoroscope(request);
    }

}
