package com.moskalyuk.weatheranalyzer.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moskalyuk.weatheranalyzer.database.entity.Weather;
import com.moskalyuk.weatheranalyzer.dto.WeatherReadDto;
import com.moskalyuk.weatheranalyzer.dto.WeatherResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class WeatherResponseMapperTest {

    @Spy
    private WeatherResponseMapper weatherResponseMapper;

    private static ObjectMapper mapper;

    @BeforeAll
    static void setUp(){
        mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
    }

    @Test
    void checkMappingFromWeatherToWeatherReadDto() throws IOException {

        WeatherResponse expect = mapper.readValue(new File("src/test/resources/test.json"), WeatherResponse.class);

        Weather actual=  weatherResponseMapper.map(expect);

        assertAll(
                ()->assertEquals(expect.getDate(),actual.getDate()),
                ()->assertEquals(expect.getHumidity(),actual.getHumidity()),
                ()->assertEquals(expect.getWind(),actual.getWind()),
                ()->assertEquals(expect.getPressure(),actual.getPressure()),
                ()->assertEquals(expect.getTemp(),actual.getTemp()),
                ()->assertEquals(expect.getCondition(),actual.getCondition()),
                ()->assertEquals(expect.getLocation(),actual.getLocation())
        );
    }
}