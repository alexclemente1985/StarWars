package br.com.alexandre.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import br.com.alexandre.exceptions.NoPlanetInSwapiException;
import br.com.alexandre.exceptions.PlanetNameConflictException;
import br.com.alexandre.exceptions.PlanetNotFoundException;
import br.com.alexandre.models.Planet;
import br.com.alexandre.models.SwapiPlanets;
import br.com.alexandre.repositories.PlanetRepository;
import br.com.alexandre.services.PlanetService;
import br.com.alexandre.services.impl.SwapiServiceImpl;
import br.com.alexandre.utils.Messages;

@Service
public class PlanetServiceImpl implements PlanetService {
	@Autowired
	private PlanetRepository planetRepository;
	@Autowired 
	private SwapiServiceImpl swapiServiceImpl;
	
	@Autowired 
	private Messages messages;
	
	
	@Override
	public Planet create(Planet planetInput) throws RestClientException, NoPlanetInSwapiException {
		
		String pname = planetInput.getName();
		
		SwapiPlanets swapiPlanet = swapiServiceImpl.getSwapiPlanet(pname);
		
		List<Planet> planets = planetRepository.findByName(pname);
		
		if(planets.size()>0) {
			Planet p = planets.get(0);
			
			String errMsg = messages.planetNameConflict(p.getName(), p.getId());
			
			throw new PlanetNameConflictException(errMsg);
		}
		
		Planet newPlanet = planetRepository.save(planetInput);
		
		int fCount = swapiPlanet.getFilmCount(); 
		
		newPlanet.setFilmCount(fCount);
		return newPlanet;
		
	}

	@Override
	public Planet findById(String Id) throws RestClientException, NoPlanetInSwapiException {
		
		Boolean bplanet = planetRepository.findById(Id).isPresent();
				
		if(!bplanet) {
			
			String errMsg = messages.planetIdNotFound(Id);
			
			throw new PlanetNameConflictException(errMsg);
		}
		
		Planet planet = planetRepository.findOneById(Id);
		
		String pname = planet.getName();
		
		SwapiPlanets swapiPlanet = swapiServiceImpl.getSwapiPlanet(pname);
		
		int fCount = swapiPlanet.getFilmCount();
		planet.setFilmCount(fCount);
		
		return planet;
		
		//return this.planetRepository.findById(id);
	}

	@Override
	public Planet findByName(String name) throws RestClientException, NoPlanetInSwapiException {
		
		List<Planet> planets = planetRepository.findByName(name);
		
		if(planets.size()==0) {
			
			String errMsg = messages.planetNameNotFound(name);
			
			throw new PlanetNotFoundException(errMsg);
		}
		
		Planet planet = planets.get(0);
		String pname = planet.getName();
		
		SwapiPlanets swapiPlanet = swapiServiceImpl.getSwapiPlanet(pname);
		
		int fCount = swapiPlanet.getFilmCount(); 
		
		planet.setFilmCount(fCount);
		return planet;
				
	}

	@Override
	public List<Planet> findAll() throws RestClientException, NoPlanetInSwapiException {
		List<Planet> planets = planetRepository.findAll();
		
		if(planets.size()==0) {
			String errMsg = messages.emptyDatabase();
			
			throw new PlanetNotFoundException(errMsg);
		}
		
		for(Planet planet:planets) {
			
			String pname = planet.getName();
			SwapiPlanets swapiPlanet =swapiServiceImpl.getSwapiPlanet(pname);
			
			int fCount = swapiPlanet.getFilmCount(); 
			
			planet.setFilmCount(fCount);
			
		}
		
		return planets;
	}

	@Override
	public void removeById(String Id) throws RestClientException,NoPlanetInSwapiException{
		
		//Planet planet = planetRepository.findOneById(Id);
		Boolean bplanet = planetRepository.existsById(Id);
		
		if (!bplanet) {
			
			String errMsg = messages.planetIdNotFound(Id);
			
			throw new PlanetNameConflictException(errMsg);
		}
		
		planetRepository.deleteById(Id);		
	}
	
	@Override
	public void removeByName(String name) throws RestClientException,NoPlanetInSwapiException{
		
		List<Planet> planets = planetRepository.findByName(name);
		
		if (planets.size()==0) {
			
			String errMsg = messages.planetNameNotFound(name);
			
			throw new PlanetNameConflictException(errMsg);
		}
		
		planetRepository.deleteByName(name);		
	}


}
