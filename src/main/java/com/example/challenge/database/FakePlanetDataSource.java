package com.example.challenge.database;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.example.challenge.planet.PlanetDataModel;
import com.example.challenge.planet.PlanetDataSource;

public class FakePlanetDataSource implements PlanetDataSource {

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

    public List<PlanetDataModel> getPlanetsByName(String name) {
        return Collections.emptyList();
    }

    @Override
    public void removeById(Long id) {
    }

}
