package com.example.tvmaze;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Mapper {

    public static List<Object> mapListOfShows (String url) {
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);
        JsonParser showParser = JsonParserFactory.getJsonParser();
        List<Object> jsonList = showParser.parseList(response);
        return jsonList;
    }

   public static Map<String, Object> mapShowFromList(Object show) {
       ObjectMapper objectMapper = new ObjectMapper();
       String mappedData;
       try {
           mappedData = objectMapper.writeValueAsString(show);
       } catch (JsonProcessingException e) {
           throw new RuntimeException(e);
       }
       JsonParser showParser = JsonParserFactory.getJsonParser();
       Map <String, Object> jsonMap = showParser.parseMap(mappedData);
       return jsonMap;
    }

    public static Map<String, Object> mapSingleShow(String url) {
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);
        JsonParser jsonParser = JsonParserFactory.getJsonParser();
        Map <String, Object> jsonMap = jsonParser.parseMap(response);
        return jsonMap;
    }
}
