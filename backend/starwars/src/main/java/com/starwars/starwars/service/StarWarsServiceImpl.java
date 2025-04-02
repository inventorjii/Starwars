package com.starwars.starwars.service;

import com.starwars.starwars.cache.SwapiCache;
import com.starwars.starwars.clients.SwapiFeignClient;
import com.starwars.starwars.dto.StarWarsApiOutput;
import com.starwars.starwars.dto.StarWarsApiResponse;
import com.starwars.starwars.dto.StarWarsEntity;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StarWarsServiceImpl implements StarWarsService{

    @Autowired
    SwapiFeignClient swapiFeignClient;

    @Autowired
    SwapiCache swapiCache;


    @CircuitBreaker(name="starWarsCircuitBreaker", fallbackMethod = "starWarsFallback")
    @Retry(name = "swapiRetry")
    @Override
    public StarWarsApiOutput getStarWarsData(String type, String name,boolean offline) {
        try{
            String cacheKey = type + "-" + name;
            if(offline || !isSwapiAccessible()){
                if(swapiCache.containsKey(cacheKey)){
                    return  swapiCache.get(cacheKey);
                }
            }

            Set<String> filmLinks = new HashSet<>();
            StarWarsApiResponse response = swapiFeignClient.getSwapidata(type,name);

            // Collecting all film links first
            for (StarWarsEntity entity : response.getResults()) {
                if (entity.getFilms() != null) {
                    filmLinks.addAll(entity.getFilms());
                }
            }

            // Iterating through each film links
            Set<String> filmNames = filmLinks.parallelStream()
                    .map(link -> link.split("/")[5])
                    .map(filmId -> swapiFeignClient.getFilm(filmId).get("title").toString())
                    .collect(Collectors.toSet());


            StarWarsApiOutput starWarsApiOutput = new StarWarsApiOutput(type,response.getCount(),
                    name,filmNames);

            swapiCache.put(cacheKey,starWarsApiOutput);
            return starWarsApiOutput;
        }catch (Exception e){
            throw e;
        }

    }

    public StarWarsApiOutput starWarsFallback(String type, String name,boolean offline,Throwable t){
        String cacheKey = type + "-" + name;

         //Return cached data if available
        if (swapiCache.containsKey(cacheKey)) {
            return swapiCache.get(cacheKey);
        }

        System.out.println("In Fallback");
        // Return fallback data if cache is empty
        return new StarWarsApiOutput(type,0,
                name,new HashSet<>());

    }


    @Override
    public boolean isSwapiAccessible() {
        try {
            swapiFeignClient.checkHealth();
            return true;
        } catch (RestClientException e) {
            return false;
        }
    }

}
