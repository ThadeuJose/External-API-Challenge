package com.example.challenge.planet;

import java.util.List;
import java.util.Optional;

import com.example.challenge.StarWarApiService;

public class PlanetUseCase {

    private PlanetDataSource planetDataSource;
    private StarWarApiService starWarApiService;

    public PlanetUseCase(PlanetDataSource planetDataSource, StarWarApiService starWarApiService) {
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

    public List<PlanetResponse> getAllPlanets() {
        return planetDataSource.getAllPlanets().stream().map(element -> {
            return new PlanetResponse(element.getName(), element.getClimate(), element.getTerrain(),
                    element.getAmountCameo());
        }).toList();
    }

    public Optional<PlanetResponse> getPlanetById(long id) {
        return planetDataSource.getPlanetById(id)
                .map(element -> new PlanetResponse(element.getName(), element.getClimate(), element.getTerrain(),
                        element.getAmountCameo()));
    }

    public List<PlanetResponse> getPlanetsByName(String name) {
        return planetDataSource.getPlanetsByName(name).stream().map(this::fromPlanetDataModelToPlanetResponse).toList();
    }

    private PlanetResponse fromPlanetDataModelToPlanetResponse(PlanetDataModel element) {
        return new PlanetResponse(element.getName(), element.getClimate(), element.getTerrain(),
                element.getAmountCameo());
    }

    public void deletePlanetById(Long id) {
        planetDataSource.removeById(id);
    }

}
