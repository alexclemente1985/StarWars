package br.com.alexandre.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import br.com.alexandre.exceptions.NoPlanetInSwapiException;
import br.com.alexandre.models.SwapiPlanets;
import br.com.alexandre.responses.SwapiSearch;
import br.com.alexandre.services.SwapiService;
import br.com.alexandre.utils.Messages;

@Service
public class SwapiServiceImpl implements SwapiService{
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private Messages messages;
	
	@Autowired
	private RestTemplate restTemplate;
	
	//@Cacheable("SwapiPlanets")
	public SwapiPlanets getSwapiPlanet(String planetName) throws NoPlanetInSwapiException, RestClientException {		
		
		String swapiUrl = "https://swapi.co/api/planets?search=" + planetName;

		log.info("GET " + swapiUrl +"...");
		
		SwapiSearch swapiSearch = restTemplate.getForObject(
				swapiUrl, SwapiSearch.class);
		
		if (swapiSearch.getCount() == 0) {
			throw new NoPlanetInSwapiException(messages.noPlanetInSwapi(planetName));
		}		
		
		for (SwapiPlanets swapiPlanet: swapiSearch.getResults()) {
			if (swapiPlanet.getName().equals(planetName)) {
				return swapiPlanet;
			}
		}
		
		throw new NoPlanetInSwapiException(messages.noPlanetInSwapi(planetName));
	}	
	
}