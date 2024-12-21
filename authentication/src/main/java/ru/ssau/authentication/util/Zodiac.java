package ru.ssau.authentication.util;

import lombok.Getter;

@Getter
public enum Zodiac {

    ARIES("Овен", 1),
    TAURUS("Телец", 2),
    GEMINI("Близнецы", 3),
    CANCER("Рак", 4),
    LEO("Лев", 5),
    VIRGO("Дева", 6),
    LIBRA("Весы", 7),
    SCORPIO("Скорпион", 8),
    SAGITTARIUS("Стрелец", 9),
    CAPRICORN("Козерог", 10),
    AQUARIUS("Водолей", 11),
    PISCES("Рыбы", 12);

    private final String name;
    private final int zodiacNumber;

    Zodiac(String name, int zodiacNumber) {
        this.name = name;
        this.zodiacNumber = zodiacNumber;
    }

}
