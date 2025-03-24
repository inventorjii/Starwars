package com.starwars.starwars.clients;


import com.starwars.starwars.dto.StarWarsApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@FeignClient(name="swapi", url = "https://swapi.dev/api/")
public interface SwapiFeignClient {

    @GetMapping("/{type}?search={name}")
    StarWarsApiResponse getSwapidata(@PathVariable String type, @PathVariable String name);

    @GetMapping("films/{filmNo}")
    Map<String,Object> getFilm(@PathVariable String filmNo);

    @GetMapping("/")
    Map<String, Object> checkHealth(); // Base URL call to check accessibility


}
