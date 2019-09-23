package br.com.silvio.ame.starwars.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.silvio.ame.starwars.model.Planet;
import br.com.silvio.ame.starwars.service.PlanetService;
import br.com.silvio.ame.starwars.swapi.PlanetPlus;
import br.com.silvio.ame.starwars.swapi.SwapiPlanet;
import br.com.silvio.ame.starwars.swapi.SwapiPlanetList;

@RestController
@RequestMapping(value="/planets")
public class PlanetController {
	@Autowired
	PlanetService service;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// handleError - Exception treatment for general exception for this controller
	@ResponseStatus(value=HttpStatus.CONFLICT, reason="General exception")
	@ExceptionHandler
	public void handleError(HttpServletRequest req, Exception ex) {
		logger.error("Exception executing url [" + req.getRequestURL() + "]:\n" + ex.getMessage());
	}

	// add - Inserts a planet in database
	@PostMapping(value="/add")
	public @ResponseBody ResponseEntity<Planet> add(@RequestBody Planet planet) {
		if ((planet.getId() == null) || (planet.getName().isEmpty())) {
			logger.error("add: Malformed planet info: " + planet);
			return new ResponseEntity<Planet>((Planet) planet, HttpStatus.BAD_REQUEST);
		}
		
		Optional<Planet> oPlanet = service.findPlanetById(planet.getId());
		
		if (oPlanet.isPresent()) {
			logger.error("add: A planet with same ID was found: " + oPlanet.get());
			return new ResponseEntity<Planet>((Planet) planet, HttpStatus.FOUND);
		}
		
		oPlanet = service.findPlanetByName(planet.getName());
		
		if (oPlanet.isPresent()) {
			logger.error("add: A planet with same name was found: " + oPlanet.get());
			return new ResponseEntity<Planet>((Planet) planet, HttpStatus.FOUND);
		}
		
		service.addPlanet(planet);
		return new ResponseEntity<Planet>((Planet) planet, HttpStatus.OK);
	}
	
	// all - gets all planets from database
	@GetMapping(value="/all")
	public List<Planet> all() {
		return service.getAllPlanets();
	}
	
	// allPlus - gets all planets from database and from swapi in PlanetPlus
	@GetMapping(value="/allplus")
	public List<PlanetPlus> allPlus() {
		List<PlanetPlus> planetPlusList = new ArrayList<PlanetPlus>();
		List<Planet> planetList = service.getAllPlanets();
		
		for (Planet planet : planetList) {
			PlanetPlus planetPlus = new PlanetPlus();
			planetPlus.setPlanet(planet);
			planetPlus.setSwapiPlanet(service.getSwapiPlanet(planet.getId()));
			planetPlusList.add(planetPlus);
		}
		
		return planetPlusList;
	}
	
	// one - gets one planet (by id) from database	
	@GetMapping(value="/one/{id}")
	public @ResponseBody ResponseEntity<Planet> one(@PathVariable(value="id") Long id) {
		if (id == null) {
			logger.error("one: ID not informed");
			return new ResponseEntity<Planet>((Planet) null, HttpStatus.BAD_REQUEST);
		}
		
		Optional<Planet> oPlanet = service.findPlanetById(id);
		
		if (oPlanet.isPresent()) {
			return new ResponseEntity<Planet>(oPlanet.get(), HttpStatus.OK);
		} else {
			logger.error("one: Planet with id " + id + " not found");
			return new ResponseEntity<Planet>((Planet) null, HttpStatus.NOT_FOUND);
		}
	}
	
	// oneplus - gets one planet (by id) from database and from swapi in PlanetPlus
	@GetMapping(value="/oneplus/{id}")
	public @ResponseBody ResponseEntity<PlanetPlus> onePlus(@PathVariable(value="id") Long id) {
		if (id == null) {
			logger.error("oneplus: Id not informed");
			return new ResponseEntity<PlanetPlus>((PlanetPlus) null, HttpStatus.BAD_REQUEST);
		}
		
		Optional<Planet> oPlanet = service.findPlanetById(id);
		
		if (oPlanet.isPresent()) {
			PlanetPlus planetPlus = new PlanetPlus(oPlanet.get(), service.getSwapiPlanet(id));
			
			return new ResponseEntity<PlanetPlus>(planetPlus, HttpStatus.OK);
		} else {
			logger.error("oneplus: Planet with id " + id + " not found");
			return new ResponseEntity<PlanetPlus>((PlanetPlus) null, HttpStatus.NOT_FOUND);
		}
	}
	
	// name - gets one planet (by name) from database	
	@GetMapping(value="/name/{name}")
	public @ResponseBody ResponseEntity<Planet> one(@PathVariable(value="name") String name) {
		if ((name == null) || name.isEmpty()) {
			logger.error("name: planet name not informed");
			return new ResponseEntity<Planet>((Planet) null, HttpStatus.BAD_REQUEST);
		}
		
		Optional<Planet> oPlanet = service.findPlanetByName(name);
		
		if (oPlanet.isPresent()) {
			return new ResponseEntity<Planet>(oPlanet.get(), HttpStatus.OK);
		} else {
			logger.error("name: Planet with name " + name + " not found");
			return new ResponseEntity<Planet>((Planet) null, HttpStatus.NOT_FOUND);
		}
	}
	
	// update - updates one planet info in database (data inside request body, including id)
	@PostMapping(value="/update")
	public @ResponseBody ResponseEntity<Planet> update(@RequestBody Planet planet) {
		if ((planet.getId() == null) || (planet.getName().isEmpty())) {
			logger.error("update: Malformed planet info: " + planet);
			return new ResponseEntity<Planet>((Planet) planet, HttpStatus.BAD_REQUEST);
		}
		
		Optional<Planet> oPlanet = service.findPlanetById(planet.getId());
		
		if (!oPlanet.isPresent()) {
			logger.error("update: Planet with id " + planet.getId() + " not found");
			return new ResponseEntity<Planet>((Planet) planet, HttpStatus.NOT_FOUND);
		}
		
		service.updatePlanet(planet);
		return new ResponseEntity<Planet>(planet, HttpStatus.OK);
	}

	// updateplus - updates one planet (by id) in database, changing its info by the one in swapi
	@GetMapping(value="/updateplus/{id}")
	public @ResponseBody ResponseEntity<Planet> updateplus(@PathVariable(value="id") Long id) {
		if (id == null) {
			logger.error("updateplus: Id not informed");
			return new ResponseEntity<Planet>((Planet) null, HttpStatus.BAD_REQUEST);
		}
		
		Optional<Planet> oPlanet = service.findPlanetById(id); 

		if (oPlanet.isPresent()) {
			Planet planet = oPlanet.get();
			if (service.updatePlanetWithSwapi(planet, null)) {
				return new ResponseEntity<Planet>(planet, HttpStatus.OK);
			} else {
				return new ResponseEntity<Planet>(planet, HttpStatus.NOT_FOUND);
			}
		} else {
			Planet planet = new Planet();
			
			planet.setId(id);
			if (service.updatePlanetWithSwapi(planet, null)) {
				return new ResponseEntity<Planet>(planet, HttpStatus.OK);
			} else {
				return new ResponseEntity<Planet>(planet, HttpStatus.NOT_FOUND);
			}
		}
	}
	
	// delete - deletes one planet (by id) from database
	@GetMapping(value="/delete/{id}")
	public @ResponseBody ResponseEntity<Planet> delete(@PathVariable(value="id") Long id) {
		if (id == null) {
			logger.error("delete: Id not informed");
			return new ResponseEntity<Planet>((Planet) null, HttpStatus.BAD_REQUEST);
		}
		
		Optional<Planet> oPlanet = service.findPlanetById(id); 

		if (oPlanet.isPresent()) {
			service.deletePlanet(id);
			return new ResponseEntity<Planet>(oPlanet.get(), HttpStatus.OK);
		} else {
			logger.error("delete: Planet with id " + id + " not found");
			return new ResponseEntity<Planet>((Planet) null, HttpStatus.NOT_FOUND);
		}
	}
	
	// importSlow - gets all planets from swapi and stores info in local database - SLOW WAY...
	@GetMapping(value="/importSlow")
	public List<PlanetPlus> importSlow() {
		logger.info(java.time.LocalDateTime.now().toString());
		
		List<PlanetPlus> planetPlusList = new ArrayList<PlanetPlus>();
		Long id = 1L;
		SwapiPlanet swapiPlanet;
		
		do {
			swapiPlanet = service.getSwapiPlanet(id);
			
			if (swapiPlanet != null) {
				Planet planet = new Planet();
				
				planet.setId(id);
				service.updatePlanetWithSwapi(planet, swapiPlanet);
				PlanetPlus planetPlus = new PlanetPlus(planet, swapiPlanet);
				planetPlusList.add(planetPlus);
				++id;
			}
		} while (swapiPlanet != null);
		
		logger.info(java.time.LocalDateTime.now().toString());

		return planetPlusList;
	}

	// importFast - gets all planets from swapi and store info in local database - FAST WAY...
	@GetMapping(value="/importFast")
	public List<PlanetPlus> importFast() {
		logger.info(java.time.LocalDateTime.now().toString());
		
		List<PlanetPlus> planetPlusList = new ArrayList<PlanetPlus>();
		SwapiPlanetList swapiPlanetList = service.getSwapiPlanetList(service.getSwapiPlanetsUrl());
		String nextUrl;
		
		do {
			SwapiPlanet[] page = swapiPlanetList.getResults();

			for (int i = 0; i < page.length; ++i) {
				SwapiPlanet swapiPlanet = page[i];
				Planet planet = new Planet();
				
				planet.setId(swapiPlanet.getId());
				service.updatePlanetWithSwapi(planet, swapiPlanet);
				
				PlanetPlus planetPlus = new PlanetPlus(planet, swapiPlanet);
				planetPlusList.add(planetPlus);
			}
			
			nextUrl = swapiPlanetList.getNext();
			if (nextUrl != null) {
				swapiPlanetList = service.getSwapiPlanetList(nextUrl);
			}
		} while (nextUrl != null);
		
		logger.info(java.time.LocalDateTime.now().toString());
		
		return planetPlusList;
	}
}
