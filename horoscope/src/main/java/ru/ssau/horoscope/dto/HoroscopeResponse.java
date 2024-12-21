package ru.ssau.horoscope.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HoroscopeResponse {
    private String horoscope;
    private String error;
}
