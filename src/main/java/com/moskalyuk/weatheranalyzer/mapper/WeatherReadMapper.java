package com.moskalyuk.weatheranalyzer.mapper;

import com.moskalyuk.weatheranalyzer.database.entity.Weather;
import com.moskalyuk.weatheranalyzer.dto.WeatherReadDto;
import org.springframework.stereotype.Component;

@Component
public class WeatherReadMapper implements Map<Weather, WeatherReadDto> {
    @Override
    public WeatherReadDto map(Weather obj) {
        return WeatherReadDto.builder()
                .id(obj.getId())
                .temp(obj.getTemp())
                .wind(obj.getWind())
                .condition(obj.getCondition())
                .date(obj.getDate())
                .humidity(obj.getHumidity())
                .location(obj.getLocation())
                .pressure(obj.getPressure())
                .build();
    }
}
