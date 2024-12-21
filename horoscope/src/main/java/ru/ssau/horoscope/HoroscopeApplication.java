package ru.ssau.horoscope;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class HoroscopeApplication {

    public static void main(String[] args) {
        SpringApplication.run(HoroscopeApplication.class, args);
    }
}
