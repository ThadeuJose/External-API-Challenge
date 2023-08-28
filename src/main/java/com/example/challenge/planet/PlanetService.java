package com.example.challenge.planet;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class PlanetService {
    private PlanetUseCase planetUseCase;

    public PlanetService(PlanetUseCase planetUseCase) {
        this.planetUseCase = planetUseCase;
    }

    public ResponseEntity<Object> createPlanet(@RequestBody PlanetRequest planetRequest) {
        Optional<Integer> id = planetUseCase.createPlanet(planetRequest);
        if (id.isPresent()) {
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id.get()).toUri();
            return ResponseEntity.created(uri).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                .body("Can't find planet with name " + planetRequest.getName());
    }

    public List<PlanetResponse> getAllPlanets() {
        List<PlanetResponse> planets = new ArrayList<>();
        planets.add(new PlanetResponse("Tatooine", "Arid", "Desert", 3));
        planets.add(new PlanetResponse("Hoth", "Frozen", "Ice plains", 4));
        planets.add(new PlanetResponse("Endor", "Temperate", "Forested moon", 3));

        return planets;
    }

    public ResponseEntity<PlanetResponse> getPlanetById(Long id) {
        Optional<PlanetResponse> planetResponse = planetUseCase.getPlanetById(id);
        if (planetResponse.isPresent()) {
            return ResponseEntity.ok(planetResponse.get());
        }
        return ResponseEntity.notFound().build();
    }

}
