package com.lilia.weather.service;

import com.lilia.weather.dto.WeatherResponse;

public interface WeatherService {
    WeatherResponse getCurrentWeather(String city);
}
