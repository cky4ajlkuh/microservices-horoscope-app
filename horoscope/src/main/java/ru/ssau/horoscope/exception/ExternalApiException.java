package ru.ssau.horoscope.exception;

public class ExternalApiException extends RuntimeException {
    public ExternalApiException() {
        super();
    }

    public ExternalApiException(String message) {
        super(message);
    }
}
