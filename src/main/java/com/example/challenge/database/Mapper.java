package com.example.challenge.database;

import com.example.challenge.planet.PlanetDataModel;

public class Mapper {
    public static PlanetData fromPlanetDataModelToPlanetData(PlanetDataModel planetDataModel) {
        return new PlanetData(planetDataModel.getName(), planetDataModel.getClimate(),
                planetDataModel.getTerrain(), planetDataModel.getAmountCameo());
    }

    public static PlanetDataModel fromPlanetDataToPlanetDataModel(PlanetData planetData) {
        return new PlanetDataModel(planetData.getName(), planetData.getClimate(),
                planetData.getTerrain(), planetData.getAmountCameo());
    }
}
