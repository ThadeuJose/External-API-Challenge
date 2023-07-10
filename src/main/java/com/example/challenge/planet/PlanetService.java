package com.example.challenge.planet;

import java.util.Optional;

import com.example.challenge.StarWarApiService;

public class PlanetService {

    PlanetDataSource planetDataSource;
    StarWarApiService starWarApiService;

    public PlanetService(PlanetDataSource planetDataSource, StarWarApiService starWarApiService) {
        this.planetDataSource = planetDataSource;
        this.starWarApiService = starWarApiService;
    }

    public Optional<Integer> createPlanet(PlanetRequest planetRequest) {
        String name = planetRequest.getName();
        Optional<Integer> amountCameo = starWarApiService.getAmountCameo(name);
        if (amountCameo.isPresent()) {
            PlanetDataModel planetDataModel = new PlanetDataModel(
                    name,
                    planetRequest.getClimate(),
                    planetRequest.getTerrain(),
                    amountCameo.get());
            int id = planetDataSource.save(planetDataModel);
            return Optional.of(id);
        }
        return Optional.empty();
    }

}
