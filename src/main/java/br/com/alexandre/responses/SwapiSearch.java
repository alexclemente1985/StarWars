package br.com.alexandre.responses;

import br.com.alexandre.models.SwapiPlanets;

public class SwapiSearch {
	private int count;
	private SwapiPlanets[] results;
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public SwapiPlanets[] getResults() {
		return results;
	}
	public void setResults(SwapiPlanets[] results) {
		this.results = results;
	}
	
	
	
}
