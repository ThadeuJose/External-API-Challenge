package com.example.challenge.planet;

import java.net.URI;
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

}
