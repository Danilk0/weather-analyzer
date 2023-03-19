package com.moskalyuk.weatheranalyzer.mapper;

import com.moskalyuk.weatheranalyzer.database.entity.Weather;
import com.moskalyuk.weatheranalyzer.dto.WeatherResponse;
import org.springframework.stereotype.Component;


@Component
public class WeatherResponseMapper implements Map<WeatherResponse, Weather> {
    @Override
    public Weather map(WeatherResponse obj) {
        return Weather.builder()
                .temp(obj.getTemp())
                .wind(obj.getWind())
                .pressure(obj.getPressure())
                .humidity(obj.getHumidity())
                .condition(obj.getCondition())
                .location(obj.getLocation())
                .date(obj.getDate())
                .build();
    }
}
