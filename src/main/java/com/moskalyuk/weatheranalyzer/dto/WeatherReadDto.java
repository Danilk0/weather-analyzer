package com.moskalyuk.weatheranalyzer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class WeatherReadDto {

    private Integer id;
    private Double temp;
    private Double wind;
    private Double pressure;
    private Double humidity;
    private String condition;
    private String location;
    private LocalDateTime date;
}
