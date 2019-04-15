package br.com.alexandre.services;

import java.util.List;

import org.springframework.web.client.RestClientException;

import br.com.alexandre.exceptions.NoPlanetInSwapiException;
import br.com.alexandre.models.Planet;

public interface PlanetService {
	Planet create(Planet planetInput) throws RestClientException,NoPlanetInSwapiException;
	
	Planet findById(String Id) throws RestClientException,NoPlanetInSwapiException;
	
	Planet findByName(String name) throws RestClientException,NoPlanetInSwapiException;
	
	List<Planet> findAll() throws RestClientException,NoPlanetInSwapiException;
	
	void removeById(String id) throws RestClientException,NoPlanetInSwapiException;
	
	void removeByName(String name) throws RestClientException,NoPlanetInSwapiException;
}
