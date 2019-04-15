package br.com.alexandre.services;

import org.springframework.web.client.RestClientException;

import br.com.alexandre.exceptions.NoPlanetInSwapiException;
import br.com.alexandre.models.SwapiPlanets;

public interface SwapiService {
		
	SwapiPlanets getSwapiPlanet(String planetName) throws NoPlanetInSwapiException, RestClientException;
	
}
