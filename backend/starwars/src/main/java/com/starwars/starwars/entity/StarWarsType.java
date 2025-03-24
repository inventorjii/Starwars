package com.starwars.starwars.entity;

import java.util.Arrays;

public enum StarWarsType {
    PEOPLE, PLANETS, STARSHIPS, VEHICLES, SPECIES, FILMS;

    public static boolean isValid(String value) {
        return Arrays.stream(values()).anyMatch(t -> t.name().equalsIgnoreCase(value));
    }
}
