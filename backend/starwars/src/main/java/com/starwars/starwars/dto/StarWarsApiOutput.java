package com.starwars.starwars.dto;


import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Set;

@Schema(description = "Response model for Star Wars API")
public class StarWarsApiOutput {
    @Schema(description = "Type of entity", example = "character")
    String type;
    @Schema(description = "Count", example = "2")
    int count;
    @Schema(description = "Name of the entity", example = "Luke Skywalker")
    String name;
    @Schema(description = "List of films", example = "['asd','asda']")
    Set<String> films;

    public StarWarsApiOutput(String type, int count, String name, Set<String> films) {
        this.type = type;
        this.count = count;
        this.name = name;
        this.films = films;
    }

    public String getType() {
        return type;
    }

    public int getCount() {
        return count;
    }

    public String getName() {
        return name;
    }

    public Set<String> getFilms() {
        return films;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFilms(Set<String> films) {
        this.films = films;
    }
}
