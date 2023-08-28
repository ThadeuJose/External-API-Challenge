package com.example.challenge.planet;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PlanetDataSource {

    public int save(PlanetDataModel planetDataModel) {
        return 0;
    }

    public List<PlanetDataModel> getAllPlanets() {
        List<PlanetDataModel> planets = new ArrayList<>();
        planets.add(new PlanetDataModel("Tatooine", "Arid", "Desert", 3));
        planets.add(new PlanetDataModel("Hoth", "Frozen", "Ice plains", 4));
        planets.add(new PlanetDataModel("Endor", "Temperate", "Forested moon", 3));
        return planets;

    }

    public Optional<PlanetDataModel> getPlanetById(Long id) {
        return Optional.empty();
    }

}
