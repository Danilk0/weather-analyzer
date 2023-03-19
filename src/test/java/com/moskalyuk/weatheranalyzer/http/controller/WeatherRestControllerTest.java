package com.moskalyuk.weatheranalyzer.http.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.moskalyuk.weatheranalyzer.dto.AverageWeather;
import com.moskalyuk.weatheranalyzer.dto.WeatherDateFilter;
import com.moskalyuk.weatheranalyzer.dto.WeatherReadDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class WeatherRestControllerTest {

    private final MockMvc mockMvc;

    @Autowired
    public WeatherRestControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    private String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        return objectMapper.writeValueAsString(obj);
    }

    private  <T> T mapFromJson(String json, Class<T> clazz)
            throws IOException {

        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        return objectMapper.readValue(json, clazz);
    }

    @Test
    public void findActualWeather() throws Exception {
        String uri = "/weather";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();
        WeatherReadDto dto = mapFromJson(content, WeatherReadDto.class);
        assertNotNull(dto);
    }
    @Test
    public void findWeather() throws Exception {
        String uri = "/weather/average";
        WeatherDateFilter filter = WeatherDateFilter.builder()
                .startDate(LocalDate.now())
                .finishDate(LocalDate.now())
                .build();
        String inputJson = mapToJson(filter);
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders
                        .get(uri)
                        .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)
                        .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();
        AverageWeather result = mapFromJson(content, AverageWeather.class);
        assertNotNull(result);
    }

}