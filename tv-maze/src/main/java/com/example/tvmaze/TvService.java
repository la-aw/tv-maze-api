package com.example.tvmaze;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

public interface TvService {

    public String getPerson(int id);

    public String getShowById(int id);

    public List<String> getShows();

    public ArrayList<Show> getShowsByRating();

    public List<Show> getShowsByGenre(String genre);


}
