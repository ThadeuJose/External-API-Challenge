package com.example.challenge.planet;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ResponseEntity<?> getPlanets(@RequestParam Optional<String> name) {
        if (name.isPresent()) {
            List<PlanetResponse> filterPlanets = planetService.getPlanetsByName(name.get());
            return ResponseEntity.ok(new QueriedPlanetListResponse(filterPlanets.size(), filterPlanets, name.get()));
        }
        List<PlanetResponse> allPlanets = planetService.getAllPlanets();
        return ResponseEntity.ok(new AllPlanetListResponse(allPlanets.size(), allPlanets));
    }

    @GetMapping(path = "planets/{id}")
    public ResponseEntity<PlanetResponse> getPlanetById(@PathVariable Long id) {
        return planetService.getPlanetById(id);
    }

}
