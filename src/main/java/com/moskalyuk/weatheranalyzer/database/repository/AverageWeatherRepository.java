package com.moskalyuk.weatheranalyzer.database.repository;

import com.moskalyuk.weatheranalyzer.dto.AverageWeather;
import com.moskalyuk.weatheranalyzer.dto.WeatherDateFilter;

import java.util.Optional;

public interface AverageWeatherRepository {
    Optional<AverageWeather> findAverageWeather(WeatherDateFilter filter);

}
