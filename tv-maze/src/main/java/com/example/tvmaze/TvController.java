package com.example.tvmaze;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.example.tvmaze.Mapper.*;

@Controller
@RequestMapping("/tv-maze")
public class TvController {

    private final String BASE_URL = "https://api.tvmaze.com/";

    @GetMapping(value = "persons/{id}", produces = "application/json")
    public @ResponseBody String getPerson(@PathVariable int id) {

        Map <String, Object> mappedShow = mapSingleShow(BASE_URL + "people/" + id);
        String personName = (String) mappedShow.get("name");

        return personName;
    }

    @GetMapping(value = "shows/{id}", produces = "application/json")
    public @ResponseBody String getShowById(@PathVariable int id) {

        Map <String, Object> mappedShow = mapSingleShow(BASE_URL + "shows/" + id);
        String showName = (String) mappedShow.get("name");

        return showName;
    }

    @GetMapping(value = "shows", produces = "application/json")
    public @ResponseBody List<String> getShows() {

        ArrayList<String> shows = new ArrayList<>();
        mapListOfShows(BASE_URL + "shows").forEach(show -> {
            Map<String, Object> mappedShow = mapShowFromList(show);
            String showName = (String) mappedShow.get("name");
                shows.add(showName);
        });

        return shows;
    }

   @GetMapping(value = "shows/popular", produces = "application/json")
    public @ResponseBody ArrayList<Show> getShowsByRating() {
       ArrayList<Show> shows = new ArrayList<>();
       mapListOfShows(BASE_URL + "shows").forEach(show -> {
           Map<String, Object> mappedShow = mapShowFromList(show);
           String showName = (String) mappedShow.get("name");
           Map averageRating  = (Map) mappedShow.get("rating");
           ArrayList<String> genres  = (ArrayList<String>) mappedShow.get("genres");
           Number averageRatingNumber = (Number) averageRating.get("average");
           if(averageRatingNumber != null) {
               Show s = new Show(showName, averageRatingNumber.doubleValue(), genres);
               shows.add(s);
           }
       });
       shows.sort((Show o1, Show o2)-> Double.compare(o2.getRating(), o1.getRating()));
       return shows;

    }


    @GetMapping(value = "shows/genre", produces = "application/json")
    public @ResponseBody List<Show> getShowsByGenre(@RequestParam String genre) {

        ArrayList<Show> showsByGenre = new ArrayList<>();
        mapListOfShows(BASE_URL + "shows").forEach(show -> {
            Map<String, Object> mappedShow = mapShowFromList(show);
            String showName = (String) mappedShow.get("name");
            Map averageRating  = (Map) mappedShow.get("rating");
            ArrayList<String> genres  = (ArrayList<String>) mappedShow.get("genres");
            Number averageRatingNumber = (Number) averageRating.get("average");

            List<String> lowerCaseList = new ArrayList<>();
            genres.forEach(str -> lowerCaseList.add(str.toLowerCase()));
            if(averageRatingNumber != null) {
                if (lowerCaseList.contains(genre)) {
                    Show s = new Show(showName, averageRatingNumber.doubleValue(), genres);
                    showsByGenre.add(s);
                }
            }
        });
        return showsByGenre;
    }




}

