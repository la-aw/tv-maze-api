package com.example.tvmaze;


import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;



@Controller
@RequestMapping("/tv-maze")
@Validated
public class TvController {

    TvServiceImpl tvServiceImpl = new TvServiceImpl();

    @GetMapping(value = "persons/{id}", produces = "application/json")
    public @ResponseBody String getPerson(@PathVariable @Positive Integer id) {
        return tvServiceImpl.getPerson(id);
    }

    @GetMapping(value = "shows/{id}", produces = "application/json")
    public @ResponseBody String getShowById(@PathVariable  @Positive Integer id) { return tvServiceImpl.getShowById(id); }

    @GetMapping(value = "shows", produces = "application/json")
    public @ResponseBody List<String> getShows() {
        return tvServiceImpl.getShows();
    }

    @GetMapping(value = "shows/popular", produces = "application/json")
    public @ResponseBody ArrayList<Show> getShowsByRating() {
        return tvServiceImpl.getShowsByRating();
    }


    @GetMapping(value = "shows/genre", produces = "application/json")
    public @ResponseBody List<Show> getShowsByGenre(@RequestParam(required = true, defaultValue = "comedy") String genre) {
        return tvServiceImpl.getShowsByGenre(genre);
    }
}

