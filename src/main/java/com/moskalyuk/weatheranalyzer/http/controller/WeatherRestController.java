package com.moskalyuk.weatheranalyzer.http.controller;

import com.moskalyuk.weatheranalyzer.dto.AverageWeather;
import com.moskalyuk.weatheranalyzer.dto.WeatherDateFilter;
import com.moskalyuk.weatheranalyzer.dto.WeatherReadDto;
import com.moskalyuk.weatheranalyzer.service.WeatherService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController()
@RequestMapping("/weather")
@RequiredArgsConstructor
public class WeatherRestController {

    private final WeatherService weatherService;

    @GetMapping()
    public WeatherReadDto findActualWeather() {
        return weatherService.findActualWeather()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/average")
    public AverageWeather findWeather(@RequestBody WeatherDateFilter filter) {
        return weatherService.findAverageTemp(filter)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

}
