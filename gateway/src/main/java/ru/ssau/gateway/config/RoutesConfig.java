package ru.ssau.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoutesConfig {

    public RouteLocator configRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(predicateSpec -> predicateSpec.path("/authentication/**").uri("lb://AUTHENTICATION"))
                .route(predicateSpec -> predicateSpec.path("/horoscope/**").uri("lb://HOROSCOPE"))
                .route(predicateSpec -> predicateSpec.path("/news/**").uri("lb://NEWS"))
                .build();
    }
}
