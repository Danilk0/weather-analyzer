package com.moskalyuk.weatheranalyzer.database.repository;

import com.moskalyuk.weatheranalyzer.database.entity.Weather;
import com.moskalyuk.weatheranalyzer.dto.AverageWeather;
import com.moskalyuk.weatheranalyzer.dto.WeatherDateFilter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.AbstractJpaQuery;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface WeatherRepository extends
        CrudRepository<Weather,Integer>,
        JpaRepository<Weather,Integer>,
        AverageWeatherRepository {

    List<Weather> findFirstByOrderByDateDesc();

    Optional<Weather> findWeatherByDate(LocalDateTime date);
    Boolean existsWeatherByDate(LocalDateTime date);

}
