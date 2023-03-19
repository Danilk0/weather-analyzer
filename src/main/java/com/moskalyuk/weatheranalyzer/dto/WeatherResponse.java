package com.moskalyuk.weatheranalyzer.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;


@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@AllArgsConstructor
public class WeatherResponse {

    @JsonProperty("location")
    Location location;

    @JsonProperty("current")
    Current current;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @NoArgsConstructor
    @Setter
    class Location{
        @JsonProperty("name")
        String name;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @NoArgsConstructor
    @Setter
    class Current{

        @JsonProperty("temp_c")
        Double temp;

        @JsonProperty("condition")
        Condition condition;

        @JsonProperty("wind_kph")
        Double wind;

        @JsonProperty("pressure_in")
        Double pressure;

        @JsonProperty("humidity")
        Double humidity;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
        @JsonProperty("last_updated")
        LocalDateTime date;

        @JsonIgnoreProperties(ignoreUnknown = true)
        @NoArgsConstructor
        @Setter
        class Condition{
            @JsonProperty("text")
            String text;
        }
    }

    public String getLocation(){
        return location.name;
    }

    public Double getTemp(){
        return current.temp;
    }
    public String getCondition() {
        return current.condition.text;
    }

    public Double getWind() {
        return current.wind;
    }

    public Double getPressure() {
        return current.pressure;
    }

    public Double getHumidity() {
        return current.humidity;
    }

    public LocalDateTime getDate(){
        return current.date;
    }
}
