package com.example.tvmaze.Service;

import com.example.tvmaze.Dto.Show;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.example.tvmaze.Mapper.Mapper.*;

public class TvServiceImpl implements TvService {

    private final String BASE_URL = "https://api.tvmaze.com/";
    @Override
    public String getPerson(int id) {
        Map<String, Object> mappedShow = mapSingleShow(BASE_URL + "people/" + id);
        String personName = (String) mappedShow.get("name");
        return personName;
    }

    @Override
    public String getShowById(int id) {
        Map <String, Object> mappedShow = mapSingleShow(BASE_URL + "shows/" + id);
        String showName = (String) mappedShow.get("name");
        return showName;
    }

    @Override
    public List<String> getShows() {
        ArrayList<String> shows = new ArrayList<>();
        mapListOfShows(BASE_URL + "shows").forEach(show -> {
            Map<String, Object> mappedShow = mapShowFromList(show);
            String showName = (String) mappedShow.get("name");
            shows.add(showName);
        });
        return shows;
    }

    @Override
    public ArrayList<Show> getShowsByRating() {
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

    @Override
    public List<Show> getShowsByGenre(String genre) {
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
