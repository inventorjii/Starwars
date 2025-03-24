package com.starwars.starwars.dto;

import java.util.List;

public class StarWarsApiResponse {
    private int count;
    private List<StarWarsEntity> results;

    public List<StarWarsEntity> getResults() {
        return results;
    }

    public void setResults(List<StarWarsEntity> results) {
        this.results = results;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}