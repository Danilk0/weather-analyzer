package com.moskalyuk.weatheranalyzer.service;

import com.moskalyuk.weatheranalyzer.database.entity.Weather;
import com.moskalyuk.weatheranalyzer.database.repository.WeatherRepository;
import com.moskalyuk.weatheranalyzer.dto.AverageWeather;
import com.moskalyuk.weatheranalyzer.dto.WeatherDateFilter;
import com.moskalyuk.weatheranalyzer.dto.WeatherReadDto;
import com.moskalyuk.weatheranalyzer.dto.WeatherResponse;
import com.moskalyuk.weatheranalyzer.mapper.WeatherReadMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
class WeatherServiceTest {

    @InjectMocks
    private  WeatherService service;

    @Mock
    private WeatherRepository repository;

    @Spy
    private WeatherReadMapper weatherReadMapper;

    @Test
    public void shouldFindAndReturnActualWeather(){
        final Weather expectedWeather= Weather.builder()
                .temp(1.1)
                .location("Minsk")
                .wind(2.4)
                .condition("Test")
                .date(LocalDateTime.MIN)
                .humidity(3.5)
                .pressure(4.3)
                .build();
        when(repository.findFirstByOrderByDateDesc()).thenReturn(List.of((expectedWeather)));

        final Optional<WeatherReadDto> actual = service.findActualWeather();

        assertThat(actual.get()).usingRecursiveComparison().isEqualTo(expectedWeather);
        verify(repository, times(1)).findFirstByOrderByDateDesc();
        verifyNoMoreInteractions(repository);

    }

    @Test
    public void shouldFindCountAndReturnAverageWeather(){

        final AverageWeather expectedAverageWeather= AverageWeather.builder()
                .avgHumidity(1.1)
                .avgTemp(1.1)
                .avgWind(1.1)
                .avgPressure(1.1)
                .build();

        final WeatherDateFilter filter=WeatherDateFilter.builder()
                .finishDate(LocalDate.MAX)
                .startDate(LocalDate.MIN)
                .build();
        when(repository.findAverageWeather(any(WeatherDateFilter.class))).thenReturn(Optional.of(expectedAverageWeather));

        final Optional<AverageWeather> actual = service.findAverageTemp(filter);

        assertThat(actual.get()).usingRecursiveComparison().isEqualTo(expectedAverageWeather);
        verify(repository, times(1)).findAverageWeather(any(WeatherDateFilter.class));
        verifyNoMoreInteractions(repository);

    }



}