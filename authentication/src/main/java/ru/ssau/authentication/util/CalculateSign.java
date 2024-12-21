package ru.ssau.authentication.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalculateSign {
    public static int getZodiacSign(String date) {
        int month = getDate(date).get(Calendar.MONTH) + 1;
        int day = getDate(date).get(Calendar.DAY_OF_MONTH);
        if ((month == 1 && day >= 20) || (month == 2 && day <= 18)) {
            return Zodiac.AQUARIUS.getZodiacNumber();
        } else if ((month == 2 && day >= 19) || (month == 3 && day <= 20)) {
            return Zodiac.PISCES.getZodiacNumber();
        } else if ((month == 3 && day >= 21) || (month == 4 && day <= 19)) {
            return Zodiac.ARIES.getZodiacNumber();
        } else if ((month == 4 && day >= 20) || (month == 5 && day <= 20)) {
            return Zodiac.TAURUS.getZodiacNumber();
        } else if ((month == 5 && day >= 21) || (month == 6 && day <= 20)) {
            return Zodiac.GEMINI.getZodiacNumber();
        } else if ((month == 6 && day >= 21) || (month == 7 && day <= 22)) {
            return Zodiac.CANCER.getZodiacNumber();
        } else if ((month == 7 && day >= 23) || (month == 8 && day <= 22)) {
            return Zodiac.LEO.getZodiacNumber();
        } else if ((month == 8 && day >= 23) || (month == 9 && day <= 22)) {
            return Zodiac.VIRGO.getZodiacNumber();
        } else if ((month == 9 && day >= 23) || (month == 10 && day <= 22)) {
            return Zodiac.LIBRA.getZodiacNumber();
        } else if ((month == 10 && day >= 23) || (month == 11 && day <= 21)) {
            return Zodiac.SCORPIO.getZodiacNumber();
        } else if ((month == 11 && day >= 22) || (month == 12 && day <= 21)) {
            return Zodiac.SAGITTARIUS.getZodiacNumber();
        } else if ((month == 12 && day >= 22) || (month == 1 && day <= 19)) {
            return Zodiac.CAPRICORN.getZodiacNumber();
        }
        return 0;
    }

    private static Calendar getDate(String date) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(format.parse(date));
            return calendar;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
