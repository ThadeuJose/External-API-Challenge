package com.example.challenge.planet;

import java.net.URI;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class PlanetController {

    private PlanetService planetService;

    public PlanetController(PlanetService planetService) {
        this.planetService = planetService;
    }

    @PostMapping(path = "planet", consumes = "application/json;charset=UTF-8")
    public ResponseEntity<Object> createPlanet(@RequestBody PlanetRequest planetRequest) {
        Optional<Integer> id = planetService.createPlanet(planetRequest);
        if (id.isPresent()) {
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
            return ResponseEntity.created(uri).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                .body("Can't find planet with name " + planetRequest.getName());
    }
}
