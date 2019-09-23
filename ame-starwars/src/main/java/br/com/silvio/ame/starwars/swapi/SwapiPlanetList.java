package br.com.silvio.ame.starwars.swapi;

public class SwapiPlanetList {
	private Long count;
	private String next;
	private String previous;
	private SwapiPlanet[] results;

	public Long getCount() {
		return count;
	}
	
	public void setCount(Long count) {
		this.count = count;
	}
	
	public String getNext() {
		return next;
	}
	
	public void setNext(String next) {
		this.next = next;
	}
	
	public String getPrevious() {
		return previous;
	}
	
	public void setPrevious(String previous) {
		this.previous = previous;
	}
	
	public SwapiPlanet[] getResults() {
		return results;
	}
	
	public void setResults(SwapiPlanet[] results) {
		this.results = results;
	}
}
