package com.moskalyuk.weatheranalyzer.mapper;

import com.moskalyuk.weatheranalyzer.database.entity.Weather;
import com.moskalyuk.weatheranalyzer.dto.WeatherReadDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class WeatherReadMapperTest {

    @Spy
    private WeatherReadMapper weatherReadMapper;

    @Test
    void checkMappingFromWeatherToWeatherReadDto() {
        Weather expect = Weather.builder()
                .temp(25.0)
                .wind(30.0)
                .pressure(20.0)
                .humidity(15.0)
                .condition("Test")
                .location("Test")
                .date(LocalDateTime.now())
                .build();


        WeatherReadDto actual=  weatherReadMapper.map(expect);
        assertThat(actual).isEqualToComparingFieldByField(expect);
    }
}