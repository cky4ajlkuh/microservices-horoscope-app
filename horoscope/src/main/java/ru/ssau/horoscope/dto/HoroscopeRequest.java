package ru.ssau.horoscope.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HoroscopeRequest {
    private String day;
    private int sign;
}
