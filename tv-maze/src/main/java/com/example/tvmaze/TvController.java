package com.example.tvmaze;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.json.JsonParseException;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Stream;

@Controller
@RequestMapping("/tv-maze")
public class TvController {

    private final String BASE_URL = "https://api.tvmaze.com/";

    @GetMapping(value = "persons/{id}", produces = "application/json")
    public @ResponseBody String getPerson(@PathVariable int id) {

        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(BASE_URL + "people/" + id, String.class);
        JsonParser jsonParser = JsonParserFactory.getJsonParser();
        Map <String, Object> jsonMap = jsonParser.parseMap(response);
        String personName = (String) jsonMap.get("name");

        return personName;
    }

    @GetMapping(value = "shows/{id}", produces = "application/json")
    public @ResponseBody String getShowById(@PathVariable int id) {

        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(BASE_URL + "shows/" + id, String.class);
        JsonParser jsonParser = JsonParserFactory.getJsonParser();
        Map <String, Object> jsonMap = jsonParser.parseMap(response);
        String showName = (String) jsonMap.get("name");

        return showName;
    }

    @GetMapping(value = "shows", produces = "application/json")
    public @ResponseBody List<String> getShows() {

        ArrayList<String> showsByRating = new ArrayList<>();
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(BASE_URL + "shows", String.class);
        JsonParser showParser = JsonParserFactory.getJsonParser();
        List<Object> jsonList = showParser.parseList(response);

        jsonList.forEach(show -> {
            ObjectMapper objectMapper = new ObjectMapper();
            String mappedData;
            try {
                mappedData = objectMapper.writeValueAsString(show);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            Map <String, Object> jsonMap = showParser.parseMap(mappedData);
            String showName = (String) jsonMap.get("name");
                showsByRating.add(showName);

        });

        return showsByRating;
    }

   @GetMapping(value = "shows/popular", produces = "application/json")
    public @ResponseBody List<String> getShowsByRating() {

        ArrayList<String> showsByRating = new ArrayList<>();

        return showsByRating;
    }

    @GetMapping(value = "shows/genre", produces = "application/json")
    public @ResponseBody List<String> getShowsByGenre(@RequestParam String genre) {

        ArrayList<String> showsByGenre = new ArrayList<>();

        return showsByGenre;
    }




}

