package com.starwars.starwars.dto;

import java.util.List;


public class StarWarsEntity {
    private String name;
    private List<String> films;

    public String getName() {
        return name;
    }

    public List<String> getFilms() {
        return films;
    }
}