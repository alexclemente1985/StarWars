package br.com.alexandre.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.alexandre.exceptions.NoPlanetInSwapiException;
import br.com.alexandre.models.Planet;
import br.com.alexandre.services.impl.PlanetServiceImpl;

@RestController
@RequestMapping("/planets")
public class PlanetController {
		
	@Autowired
	private PlanetServiceImpl planetServiceImpl;
	
	@RequestMapping(method=RequestMethod.POST, value="/insert")
	ResponseEntity<?> create(@RequestBody Planet planetInput) throws RestClientException, NoPlanetInSwapiException{
		
		Planet newPlanet = planetServiceImpl.create(planetInput);
		
		URI location = ServletUriComponentsBuilder
						.fromCurrentRequest()
						.replacePath("/planets/{id}")
						.buildAndExpand(newPlanet.getId()).toUri();
		
		return ResponseEntity.created(location).body(newPlanet);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/idSearch/{id}")
	Planet findById(@PathVariable(name="id") String id) throws RestClientException, NoPlanetInSwapiException{	
		return planetServiceImpl.findById(id);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/nameSearch/{name}")
	Planet findByName(@PathVariable(name="name") String name) throws RestClientException, NoPlanetInSwapiException{
		return planetServiceImpl.findByName(name);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/searchAll")
	List<Planet> findAll() throws RestClientException, NoPlanetInSwapiException{
		return planetServiceImpl.findAll();
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/deleteByName/{name}")
	//@ResponseStatus(HttpStatus.NO_CONTENT)
	void removeByName(@PathVariable(name="name") String name) throws RestClientException, NoPlanetInSwapiException{
		planetServiceImpl.removeByName(name);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/deleteById/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	void removeById(@PathVariable(name="id") String id) throws RestClientException, NoPlanetInSwapiException{
		planetServiceImpl.removeById(id);
	}
}
