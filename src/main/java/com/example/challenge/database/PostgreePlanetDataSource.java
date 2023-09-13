package com.example.challenge.database;

import java.util.List;
import java.util.Optional;

import com.example.challenge.planet.PlanetDataModel;
import com.example.challenge.planet.PlanetDataSource;

public class PostgreePlanetDataSource implements PlanetDataSource {

    private PlanetRepository repository;

    public PostgreePlanetDataSource(PlanetRepository repository) {
        this.repository = repository;
    }

    public int save(PlanetDataModel planetDataModel) {
        PlanetData planetData = repository.save(Mapper.fromPlanetDataModelToPlanetData(planetDataModel));
        return planetData.getId().intValue();
    }

    public List<PlanetDataModel> getAllPlanets() {
        return repository.findAll().stream().map(Mapper::fromPlanetDataToPlanetDataModel).toList();
    }

    public Optional<PlanetDataModel> getPlanetById(Long id) {
        return repository.findById(id).map(Mapper::fromPlanetDataToPlanetDataModel);
    }

    public List<PlanetDataModel> getPlanetsByName(String name) {
        return repository.findByNameContainingIgnoreCase(name).stream().map(Mapper::fromPlanetDataToPlanetDataModel)
                .toList();
    }

    @Override
    public void removeById(Long id) {
        repository.deleteById(id);
    }

}
