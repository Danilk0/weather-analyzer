package com.moskalyuk.weatheranalyzer.database.repository;

import com.moskalyuk.weatheranalyzer.dto.AverageWeather;
import com.moskalyuk.weatheranalyzer.dto.WeatherDateFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@RequiredArgsConstructor
public class AverageWeatherRepositoryImpl implements AverageWeatherRepository {

    private static String FIND_AVG_WEATHER_BY_DATE = """
               select avg(temp) as temp,
                      avg(humidity) as humidity,
                      avg(pressure) as pressure,
                      avg(wind) as wind from (select
                   extract(doy from  w.date),
                   avg(w.temp) as temp,
                   avg(w.humidity) as humidity,
                   avg(w.pressure) as pressure,
                   avg(w.wind) as wind
               from Weather w
               where date(w.date) >= ?
                 and date(w.date) <= ?
               group by extract(doy from  w.date)) as weather;  
            """;

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Optional<AverageWeather> findAverageWeather(WeatherDateFilter filter) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(
                FIND_AVG_WEATHER_BY_DATE,
                new Object[]{filter.getStartDate(),filter.getFinishDate()},
                (rs, rowNum) -> new AverageWeather(
                        rs.getDouble("temp"),
                        rs.getDouble("wind"),
                        rs.getDouble("pressure"),
                        rs.getDouble("humidity")
                        )));
    }
}
