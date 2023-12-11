package com.example.tvmaze.Service;

import com.example.tvmaze.Dto.Show;

import java.util.ArrayList;
import java.util.List;

public interface TvService {

    public String getPerson(int id);

    public String getShowById(int id);

    public List<String> getShows();

    public ArrayList<Show> getShowsByRating();

    public List<Show> getShowsByGenre(String genre);


}
