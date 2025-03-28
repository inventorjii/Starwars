package com.starwars.starwars;

import com.starwars.starwars.cache.SwapiCache;
import com.starwars.starwars.clients.SwapiFeignClient;
import com.starwars.starwars.dto.StarWarsApiOutput;
import com.starwars.starwars.dto.StarWarsApiResponse;
import com.starwars.starwars.dto.StarWarsEntity;
import com.starwars.starwars.service.StarWarsServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;


@ExtendWith(MockitoExtension.class)
class StarwarsApplicationTests {

	@Mock
	SwapiFeignClient swapiFeignClient;

	@InjectMocks  // Automatically injects mocks into StarWarsService
	StarWarsServiceImpl starWarsService;

	@Mock
	SwapiCache swapiCache;

	@Test
	void contextLoads() {}

	@Test
	public void shouldReturnValidResultsWhenValidArgumentsPassed() {
		// Given
		StarWarsApiResponse starWarsApiResponse = new StarWarsApiResponse();
		StarWarsEntity starWarsEntity = new StarWarsEntity();
		//starWarsEntity.setName("Luke Skywalker");  // Ensure the entity has a name
		List<StarWarsEntity> starWarsEntities= new ArrayList<>();
		starWarsEntities.add(starWarsEntity);

		starWarsApiResponse.setResults(starWarsEntities);

		Mockito.lenient().when(swapiCache.containsKey(any())).thenReturn(false);
		Mockito.when(swapiFeignClient.getSwapidata("People","Luke Skywalker")).thenReturn(starWarsApiResponse);

		// When
		StarWarsApiOutput starWarsApiOutput = starWarsService.getStarWarsData("People","Luke Skywalker",false);

		// Then
		assertNotNull(starWarsApiOutput);  // Ensure it's not null
		assertEquals("Luke Skywalker", starWarsApiOutput.getName());
		assertEquals("People", starWarsApiOutput.getType());
	}
}
