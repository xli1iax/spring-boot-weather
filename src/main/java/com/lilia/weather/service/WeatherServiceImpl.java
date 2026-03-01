package com.lilia.weather.service;

import com.lilia.weather.dto.OpenWeatherResponse;
import com.lilia.weather.dto.WeatherResponse;
import com.lilia.weather.exception.CityNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherServiceImpl implements WeatherService {
    private final RestTemplate restTemplate;
    private final String apiKey;
    private final String baseUrl;

    public WeatherServiceImpl(
            RestTemplate restTemplate,
            @Value("${openweather.api.key}") String apiKey,
            @Value("${openweather.base-url}") String baseUrl
    ) {
        this.restTemplate = restTemplate;
        this.apiKey = apiKey;
        this.baseUrl = baseUrl;
    }

    @Cacheable(value = "weather", key = "#city.toLowerCase()")
    @Override
    public WeatherResponse getCurrentWeather(String city) {
        String url = baseUrl +
                "?q=" + city +
                "&appid=" + apiKey +
                "&units=metric";

        OpenWeatherResponse response =
                restTemplate.getForObject(url, OpenWeatherResponse.class);

        if (response == null || response.weather().isEmpty()) {
            throw new CityNotFoundException("City not found: " + city);
        }

        double windKmh = response.wind().speed() * 3.6;

        return new WeatherResponse(
                response.weather().get(0).description(),
                response.main().temp(),
                windKmh
        );
    }
}
