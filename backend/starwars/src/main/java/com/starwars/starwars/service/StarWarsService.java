package com.starwars.starwars.service;

import com.starwars.starwars.dto.StarWarsApiOutput;

public interface StarWarsService {

    public StarWarsApiOutput getStarWarsData(String type,String name,boolean offline);

    boolean isSwapiAccessible();

}
