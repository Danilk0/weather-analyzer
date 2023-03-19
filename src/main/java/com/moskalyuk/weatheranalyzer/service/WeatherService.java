package com.moskalyuk.weatheranalyzer.service;

import com.moskalyuk.weatheranalyzer.database.entity.Weather;
import com.moskalyuk.weatheranalyzer.database.repository.WeatherRepository;
import com.moskalyuk.weatheranalyzer.dto.AverageWeather;
import com.moskalyuk.weatheranalyzer.dto.WeatherDateFilter;
import com.moskalyuk.weatheranalyzer.dto.WeatherReadDto;
import com.moskalyuk.weatheranalyzer.dto.WeatherResponse;
import com.moskalyuk.weatheranalyzer.mapper.WeatherReadMapper;
import com.moskalyuk.weatheranalyzer.mapper.WeatherResponseMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.RequestEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class WeatherService {

    private final WeatherRepository weatherRepository;

    private final RestTemplate restTemplate;

    private final WeatherResponseMapper weatherResponseMapper;

    private final WeatherReadMapper weatherReadMapper;

    @Scheduled(cron = "${scheduler.interval-in-crone}")
    @Transactional
    public void updateWeather(){
        log.info("Scheduled start");
        RequestEntity requestEntity= RequestEntity.get(URI.create("https://weatherapi-com.p.rapidapi.com/current.json?q=Minsk"))
                .header("X-RapidAPI-Key", "e7577f57ddmsh5c909b395446b4ap166f3bjsn45bee5386c7f")
                .header("X-RapidAPI-Host", "weatherapi-com.p.rapidapi.com")
                .build();
        try {
            Optional.ofNullable(restTemplate.exchange(requestEntity, WeatherResponse.class).getBody())
                    .map(weatherResponseMapper::map)
                    .map(weather -> weatherRepository.existsWeatherByDate(weather.getDate()) ? null : weatherRepository.save(weather))
                    .or(()-> {
                        log.info("Weather not update");
                        return Optional.empty();
                    });

        } catch (RestClientException e) {
            log.error("Rest client exception, request not sent");
        }
        log.info("Scheduled finish");
    }

    @Transactional
    public Optional<WeatherReadDto> findActualWeather(){
        return weatherRepository.findFirstByOrderByDateDesc().stream().findFirst()
                .map(weatherReadMapper::map);
    }

    @Transactional
    public Optional<AverageWeather> findAverageTemp(WeatherDateFilter filter){
        return weatherRepository.findAverageWeather(filter);}

}
