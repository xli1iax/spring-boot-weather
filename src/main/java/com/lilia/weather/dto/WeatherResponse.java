package com.lilia.weather.dto;

import java.math.BigDecimal;

public record WeatherResponse(
        String condition,
        double temperature,
        double wind_speed
) {
}
