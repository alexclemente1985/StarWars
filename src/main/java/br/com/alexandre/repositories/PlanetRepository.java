package br.com.alexandre.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.alexandre.models.Planet;

public interface PlanetRepository extends MongoRepository<Planet, String> {
	
	Planet findOneById(String id);
	
	void deleteById(String id);
	
	void deleteByName(String planetName);
	
	List<Planet> findByName(String name);
}
