package br.com.silvio.ame.starwars.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.silvio.ame.starwars.model.Planet;

public interface PlanetDao extends JpaRepository<Planet, Long> {
	Optional<Planet> findByName(String name);
}
