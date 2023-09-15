package com.example.challenge.planet;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class PlanetService {
    private PlanetUseCase planetUseCase;

    public PlanetService(PlanetUseCase planetUseCase) {
        this.planetUseCase = planetUseCase;
    }

    public ResponseEntity<Object> createPlanet(PlanetRequest planetRequest) {
        Optional<Integer> id = planetUseCase.createPlanet(planetRequest);
        if (id.isPresent()) {
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id.get()).toUri();
            return ResponseEntity.created(uri).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                .body("Can't find planet with name " + planetRequest.getName());
    }

    public List<PlanetResponse> getAllPlanets() {
        return planetUseCase.getAllPlanets();
    }

    public ResponseEntity<PlanetResponse> getPlanetById(Long id) {
        Optional<PlanetResponse> planetResponse = planetUseCase.getPlanetById(id);
        if (planetResponse.isPresent()) {
            return ResponseEntity.ok(planetResponse.get());
        }
        return ResponseEntity.notFound().build();
    }

    public List<PlanetResponse> getPlanetsByName(String name) {
        return planetUseCase.getPlanetsByName(name);
    }

    public ResponseEntity<PlanetResponse> deletePlanetById(Long id) {
        Optional<PlanetResponse> planetResponse = planetUseCase.getPlanetById(id);
        if (!planetResponse.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        planetUseCase.deletePlanetById(id);
        return ResponseEntity.ok().build();
    }

}
