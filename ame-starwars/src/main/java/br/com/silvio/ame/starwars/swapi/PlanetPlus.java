package br.com.silvio.ame.starwars.swapi;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.silvio.ame.starwars.model.Planet;
import br.com.silvio.ame.starwars.swapi.SwapiPlanet;

//PlanetPlus - Class that brings together database info (Planet), swapi info (SwapiPlanet)
//and film count (numberOfFilms), extracted from swapi.
public class PlanetPlus {
	private Planet planet;
	@JsonIgnore
	private SwapiPlanet swapiPlanet;
	@JsonProperty("numberoffilms")
	private Integer numberOfFilms;
	
	public PlanetPlus() {
		numberOfFilms = 0;
	}
	
	public PlanetPlus(Planet planet, SwapiPlanet swapiPlanet) {
		super();
		this.planet = planet;
		this.swapiPlanet = swapiPlanet;
	}
	
	public Planet getPlanet() {
		return planet;
	}
	
	public void setPlanet(Planet planet) {
		this.planet = planet;
	}
	
	public SwapiPlanet getSwapiPlanet() {
		return swapiPlanet;
	}
	
	public void setSwapiPlanet(SwapiPlanet swapiPlanet) {
		this.swapiPlanet = swapiPlanet;
	}

	public Integer getNumberOfFilms() {
		numberOfFilms = swapiPlanet.getFilms().size();
		return numberOfFilms;
	}
}
