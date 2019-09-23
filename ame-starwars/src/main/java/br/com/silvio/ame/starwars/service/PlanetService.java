package br.com.silvio.ame.starwars.service;

import java.util.List;
import java.util.Optional;

import br.com.silvio.ame.starwars.model.Planet;
import br.com.silvio.ame.starwars.swapi.SwapiPlanet;
import br.com.silvio.ame.starwars.swapi.SwapiPlanetList;

//PlanetService - interface to PlanetServiceImpl
public interface PlanetService {
	public String getSwapiPlanetsUrl();
	public void addPlanet(Planet planet);
	public List<Planet> getAllPlanets();
	public Optional<Planet> findPlanetById(Long id);
	public Optional<Planet> findPlanetByName(String name);
	public void deletePlanet(Long id);
	public void updatePlanet(Planet planet);
	public boolean updatePlanetWithSwapi(Planet planet, SwapiPlanet swapiPlanet);
	public SwapiPlanet getSwapiPlanet(Long id);
	public SwapiPlanetList getSwapiPlanetList(String url);
}
