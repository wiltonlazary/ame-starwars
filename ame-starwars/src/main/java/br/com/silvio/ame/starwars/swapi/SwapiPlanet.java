package br.com.silvio.ame.starwars.swapi;

import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

//SwapiPlanet - class that stores planet info extracted from swapi
@JsonIgnoreProperties(ignoreUnknown = true)
public class SwapiPlanet {
	private String name;
	@JsonProperty("rotation_period")
	private String rotationPeriod;
	@JsonProperty("orbital_period")
	private String orbitalPeriod;
	private String diameter;
	private String climate;
	private String gravity;
	private String terrain;
	@JsonProperty("surface_water")
	private String surfaceWater;
	private String population;
	private List<String> residents;
	private List<String> films;
	private String url;

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getRotationPeriod() {
		return rotationPeriod;
	}
	
	public void setRotationPeriod(String rotationPeriod) {
		this.rotationPeriod = rotationPeriod;
	}
	
	public String getOrbitalPeriod() {
		return orbitalPeriod;
	}
	
	public void setOrbitalPeriod(String orbitalPeriod) {
		this.orbitalPeriod = orbitalPeriod;
	}
	
	public String getDiameter() {
		return diameter;
	}
	
	public void setDiameter(String diameter) {
		this.diameter = diameter;
	}
	
	public String getClimate() {
		return climate;
	}
	
	public void setClimate(String climate) {
		this.climate = climate;
	}
	
	public String getGravity() {
		return gravity;
	}
	
	public void setGravity(String gravity) {
		this.gravity = gravity;
	}
	
	public String getTerrain() {
		return terrain;
	}
	
	public void setTerrain(String terrain) {
		this.terrain = terrain;
	}
	
	public String getSurfaceWater() {
		return surfaceWater;
	}
	
	public void setSurfaceWater(String surfaceWater) {
		this.surfaceWater = surfaceWater;
	}
	
	public String getPopulation() {
		return population;
	}
	
	public void setPopulation(String population) {
		this.population = population;
	}
	
	public List<String> getResidents() {
		return residents;
	}
	
	public void setResidents(List<String> residents) {
		this.residents = residents;
	}
	
	public List<String> getFilms() {
		return films;
	}
	
	public void setFilms(List<String> films) {
		this.films = films;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public Long getId() {
		Scanner scanner = new Scanner(url);
		String part = "0";
		
		scanner.useDelimiter("/");
		
		while (scanner.hasNext()) {
			part = scanner.next();
		}
		
		scanner.close();
		
		return Long.parseLong(part);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((climate == null) ? 0 : climate.hashCode());
		result = prime * result + ((diameter == null) ? 0 : diameter.hashCode());
		result = prime * result + ((films == null) ? 0 : films.hashCode());
		result = prime * result + ((gravity == null) ? 0 : gravity.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((orbitalPeriod == null) ? 0 : orbitalPeriod.hashCode());
		result = prime * result + ((population == null) ? 0 : population.hashCode());
		result = prime * result + ((residents == null) ? 0 : residents.hashCode());
		result = prime * result + ((rotationPeriod == null) ? 0 : rotationPeriod.hashCode());
		result = prime * result + ((surfaceWater == null) ? 0 : surfaceWater.hashCode());
		result = prime * result + ((terrain == null) ? 0 : terrain.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SwapiPlanet other = (SwapiPlanet) obj;
		if (climate == null) {
			if (other.climate != null)
				return false;
		} else if (!climate.equals(other.climate))
			return false;
		if (diameter == null) {
			if (other.diameter != null)
				return false;
		} else if (!diameter.equals(other.diameter))
			return false;
		if (films == null) {
			if (other.films != null)
				return false;
		} else if (!films.equals(other.films))
			return false;
		if (gravity == null) {
			if (other.gravity != null)
				return false;
		} else if (!gravity.equals(other.gravity))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (orbitalPeriod == null) {
			if (other.orbitalPeriod != null)
				return false;
		} else if (!orbitalPeriod.equals(other.orbitalPeriod))
			return false;
		if (population == null) {
			if (other.population != null)
				return false;
		} else if (!population.equals(other.population))
			return false;
		if (residents == null) {
			if (other.residents != null)
				return false;
		} else if (!residents.equals(other.residents))
			return false;
		if (rotationPeriod == null) {
			if (other.rotationPeriod != null)
				return false;
		} else if (!rotationPeriod.equals(other.rotationPeriod))
			return false;
		if (surfaceWater == null) {
			if (other.surfaceWater != null)
				return false;
		} else if (!surfaceWater.equals(other.surfaceWater))
			return false;
		if (terrain == null) {
			if (other.terrain != null)
				return false;
		} else if (!terrain.equals(other.terrain))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "SwapiPlanet [name=" + name + ", rotationPeriod=" + rotationPeriod + ", orbitalPeriod=" + orbitalPeriod
				+ ", diameter=" + diameter + ", climate=" + climate + ", gravity=" + gravity + ", terrain=" + terrain
				+ ", surfaceWater=" + surfaceWater + ", population=" + population + ", residents=" + residents
				+ ", films=" + films + ", url=" + url + "]";
	}
}
