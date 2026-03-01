package com.lilia.weather.dto;

import java.util.List;

public record OpenWeatherResponse(
        List<WeatherItem> weather,
        Main main,
        Wind wind
) {
    public record WeatherItem(String description) {}
    public record Main(double temp) {}
    public record Wind(double speed) {}
}
