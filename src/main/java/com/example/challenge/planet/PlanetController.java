package com.example.challenge.planet;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlanetController {

    private PlanetService planetService;

    public PlanetController(PlanetService planetService) {
        this.planetService = planetService;
    }

    @PostMapping(path = "planet", consumes = "application/json;charset=UTF-8")
    public ResponseEntity<Object> createPlanet(@RequestBody PlanetRequest planetRequest) {
        return planetService.createPlanet(planetRequest);
    }

    @GetMapping(path = "planets")
    public List<PlanetResponse> getPlanets() {
        return planetService.getAllPlanets();
    }
}
