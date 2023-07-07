package com.example.challenge.planet;

import com.example.challenge.StarWarApiService;

public class PlanetService {

    PlanetDataSource planetDataSource;
    StarWarApiService starWarApiService;

    public PlanetService(PlanetDataSource planetDataSource, StarWarApiService starWarApiService) {
        this.planetDataSource = planetDataSource;
        this.starWarApiService = starWarApiService;
    }

    public int createPlanet(PlanetRequest planetRequest) {
        String name = planetRequest.getName();
        int amountCameo = starWarApiService.getAmountCameo(name).get();
        return planetDataSource
                .save(new PlanetDataModel(name, planetRequest.getClimate(), planetRequest.getTerrain(), amountCameo));
    }

}
