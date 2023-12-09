package com.example.tvmaze;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.example.tvmaze.Mapper.*;

@Controller
@RequestMapping("/tv-maze")
public class TvController {

    TvServiceImpl tvServiceImpl = new TvServiceImpl();

    @GetMapping(value = "persons/{id}", produces = "application/json")
    public @ResponseBody String getPerson(@PathVariable int id) {
        return tvServiceImpl.getPerson(id);
    }

    @GetMapping(value = "shows/{id}", produces = "application/json")
    public @ResponseBody String getShowById(@PathVariable int id) {
        return tvServiceImpl.getShowById(id);
    }

    @GetMapping(value = "shows", produces = "application/json")
    public @ResponseBody List<String> getShows() {
        return tvServiceImpl.getShows();
    }

   @GetMapping(value = "shows/popular", produces = "application/json")
    public @ResponseBody ArrayList<Show> getShowsByRating() {
        return tvServiceImpl.getShowsByRating();
    }


    @GetMapping(value = "shows/genre", produces = "application/json")
    public @ResponseBody List<Show> getShowsByGenre(@RequestParam String genre) {
        return tvServiceImpl.getShowsByGenre(genre);
    }




}

