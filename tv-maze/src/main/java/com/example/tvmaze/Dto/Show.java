package com.example.tvmaze.Dto;

import com.fasterxml.jackson.annotation.JsonTypeId;
import org.springframework.util.IdGenerator;

import java.util.ArrayList;
import java.util.UUID;

public class Show {


    public Show () {
        this.name = "";
        this.genres = new ArrayList<>();
        this.rating = null;

    }

    public Show (String name) {
        this.name = name;
        this.genres = new ArrayList<>();
        this.rating = null;

    }

    public Show (String name, Double rating) {
        this.name = name;
        this.genres = new ArrayList<>();
        this.rating = rating;

    }

    public Show (String name, Double rating, ArrayList<String> genres) {
        this.name = name;
        this.genres = genres;
        this.rating = rating;

    }


    private String name;

    private ArrayList<String> genres;

    private Double rating;


    public String getName() {
        return name;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public Double getRating() { return rating; }

    @Override
    public String toString() {
        return getName() + " - " + getRating();
    }
}
