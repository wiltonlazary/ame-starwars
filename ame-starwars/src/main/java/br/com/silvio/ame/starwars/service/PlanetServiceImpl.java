package br.com.silvio.ame.starwars.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.silvio.ame.starwars.dao.PlanetDao;
import br.com.silvio.ame.starwars.model.Planet;
import br.com.silvio.ame.starwars.swapi.SwapiPlanet;
import br.com.silvio.ame.starwars.swapi.SwapiPlanetList;

//PlanetServiceImpl - Service implementation that treats planets business rules
@Service
public class PlanetServiceImpl implements PlanetService {
	private static final String SWAPI_URL_KEY = "swapi.planets.url";

	@Autowired
	private Environment env;

	@Autowired
	PlanetDao dao;
	
	@Override
	public String getSwapiPlanetsUrl() {
		return env.getProperty(SWAPI_URL_KEY);
	}
	
	@Override
	public void addPlanet(Planet planet) {
		dao.save(planet);
	}

	@Override
	public List<Planet> getAllPlanets() {
		return dao.findAll();
	}

	@Override
	public Optional<Planet> findPlanetById(Long id) {
		return dao.findById(id);
	}

	@Override
	public Optional<Planet> findPlanetByName(String name) {
		return dao.findByName(name);
	}

	@Override
	public void deletePlanet(Long id) {
		dao.deleteById(id);
	}

	@Override
	public void updatePlanet(Planet planet) {
		dao.save(planet);
	}

	@Override
	public boolean updatePlanetWithSwapi(Planet planet, SwapiPlanet swapiPlanet) {
		if (swapiPlanet == null) {
			swapiPlanet = getSwapiPlanet(planet.getId());
		}
		
		if (swapiPlanet != null) {
			planet.setName(swapiPlanet.getName());
			planet.setClimate(swapiPlanet.getClimate());
			planet.setTerrain(swapiPlanet.getTerrain());
			
			dao.save(planet);
			
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public SwapiPlanet getSwapiPlanet(Long id) {
		RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        String url = getSwapiPlanetsUrl() + id;
        
        try {
        	ResponseEntity<SwapiPlanet> res = restTemplate.exchange(url, HttpMethod.GET, entity, SwapiPlanet.class);
            if (res.getStatusCode() == HttpStatus.OK) {
            	return res.getBody();
            } else {
            	return null;
            }
        } catch(Exception e) {
        	return null;
        }
   	}

	@Override
	public SwapiPlanetList getSwapiPlanetList(String url) {
		RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        
        try {
        	ResponseEntity<SwapiPlanetList> res = restTemplate.exchange(url, HttpMethod.GET, entity, SwapiPlanetList.class);
            if (res.getStatusCode() == HttpStatus.OK) {
            	return res.getBody();
            } else {
            	return null;
            }
        } catch(Exception e) {
        	return null;
        }
   	}
}
