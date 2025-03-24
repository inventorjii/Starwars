package com.starwars.starwars.cache;

import com.starwars.starwars.dto.StarWarsApiOutput;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SwapiCache {
    private final Map<String, StarWarsApiOutput> cache = new ConcurrentHashMap<>();

    public void put(String key, StarWarsApiOutput value) {
        cache.put(key, value);
    }

    public StarWarsApiOutput get(String key) {
        return cache.get(key);
    }

    public boolean containsKey(String key) {
        return cache.containsKey(key);
    }
}