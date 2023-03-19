package com.moskalyuk.weatheranalyzer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AverageWeather {

    private Double avgTemp;
    private Double avgWind;
    private Double avgPressure;
    private Double avgHumidity;
}
