package com.example.challenge.planet;

import java.util.List;
import java.util.Optional;

public interface PlanetDataSource {
    public int save(PlanetDataModel planetDataModel);

    public List<PlanetDataModel> getAllPlanets();

    public Optional<PlanetDataModel> getPlanetById(Long id);

    public List<PlanetDataModel> getPlanetsByName(String name);

    public void removeById(Long id);
}
