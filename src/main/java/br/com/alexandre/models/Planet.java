package br.com.alexandre.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Planet {
	@Id
	private String id;
	
	@Indexed(unique=true)
	private String name;
	
	private String climate;
	private String terrain;
	
	@Transient 
	private int filmCount;
	
	public Planet() {
		
	}
	
	public Planet(String name, String climate, String terrain) {
		this.name=name;
		this.climate=climate;
		this.terrain=terrain;
	}

	public String getId() {
		return this.id;
	}

//	public void setId(String id) {
//		this.id = id;
//	}

	public String getName() {
		return name;
	}

	public void setName(String n) {
		this.name = n;
	}

	public String getClimate() {
		return climate;
	}

	public void setClimate(String clim) {
		this.climate = clim;
	}

	public String getTerrain() {
		return terrain;
	}

	public void setTerrain(String terrain) {
		this.terrain = terrain;
	}

	public int getFilmCount() {
		return filmCount;
	}

	public void setFilmCount(int filmCount) {
		this.filmCount = filmCount;
	}
	
	
}
