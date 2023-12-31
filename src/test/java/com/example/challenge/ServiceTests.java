package com.example.challenge;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.example.challenge.database.FakePlanetDataSource;
import com.example.challenge.planet.PlanetDataModel;
import com.example.challenge.planet.PlanetRequest;
import com.example.challenge.planet.PlanetResponse;
import com.example.challenge.planet.PlanetUseCase;

public class ServiceTests {

    @Test
    public void shouldReturnId() {
        FakePlanetDataSource planetDataSource = mock(FakePlanetDataSource.class);
        StarWarApiService starWarApiService = mock(StarWarApiService.class);
        PlanetUseCase planetUseCase = new PlanetUseCase(planetDataSource, starWarApiService);
        when(starWarApiService.getAmountCameo(any())).thenReturn(Optional.of(1));
        when(planetDataSource.save(any())).thenReturn(2);

        Optional<Integer> id = planetUseCase.createPlanet(new PlanetRequest("TestName", "TestClimate", "TestTerrain"));

        assertThat(id.get()).isEqualTo(2);
        verify(planetDataSource).save(new PlanetDataModel("TestName", "TestClimate", "TestTerrain", 1));
    }

    @Test
    public void shouldReturnEmptyIdIfNotFound() {
        FakePlanetDataSource planetDataSource = mock(FakePlanetDataSource.class);
        StarWarApiService starWarApiService = mock(StarWarApiService.class);
        PlanetUseCase planetUseCase = new PlanetUseCase(planetDataSource, starWarApiService);
        when(starWarApiService.getAmountCameo(any())).thenReturn(Optional.empty());
        when(planetDataSource.save(any())).thenReturn(2);

        Optional<Integer> id = planetUseCase.createPlanet(new PlanetRequest("TestName", "TestClimate", "TestTerrain"));

        assertThat(id.isPresent()).isFalse();
    }

    @Test
    public void shouldNotSaveIfPlanetIsNotFound() {
        FakePlanetDataSource planetDataSource = mock(FakePlanetDataSource.class);
        StarWarApiService starWarApiService = mock(StarWarApiService.class);
        PlanetUseCase planetUseCase = new PlanetUseCase(planetDataSource, starWarApiService);
        when(starWarApiService.getAmountCameo(any())).thenReturn(Optional.empty());
        when(planetDataSource.save(any())).thenReturn(2);

        planetUseCase.createPlanet(new PlanetRequest("TestName", "TestClimate", "TestTerrain"));

        verifyNoInteractions(planetDataSource);
    }

    @Test
    public void shouldGetListOfPlanet() {
        FakePlanetDataSource planetDataSource = mock(FakePlanetDataSource.class);
        StarWarApiService starWarApiService = mock(StarWarApiService.class);
        PlanetUseCase planetUseCase = new PlanetUseCase(planetDataSource, starWarApiService);

        List<PlanetDataModel> planets = new ArrayList<>();
        planets.add(new PlanetDataModel("Tatooine", "Arid", "Desert", 3));
        planets.add(new PlanetDataModel("Hoth", "Frozen", "Ice plains", 4));
        planets.add(new PlanetDataModel("Endor", "Temperate", "Forested moon", 3));

        when(planetDataSource.getAllPlanets()).thenReturn(planets);

        List<PlanetResponse> allPlanets = planetUseCase.getAllPlanets();

        List<PlanetResponse> planetResponses = new ArrayList<>();
        planetResponses.add(new PlanetResponse("Tatooine", "Arid", "Desert", 3));
        planetResponses.add(new PlanetResponse("Hoth", "Frozen", "Ice plains", 4));
        planetResponses.add(new PlanetResponse("Endor", "Temperate", "Forested moon", 3));

        assertThat(allPlanets).hasSameElementsAs(planetResponses);
    }

    @Test
    public void shouldGetPlanetById() {
        FakePlanetDataSource planetDataSource = mock(FakePlanetDataSource.class);
        StarWarApiService starWarApiService = mock(StarWarApiService.class);
        PlanetUseCase planetUseCase = new PlanetUseCase(planetDataSource, starWarApiService);

        PlanetDataModel planet = new PlanetDataModel("Tatooine", "Arid", "Desert", 3);

        when(planetDataSource.getPlanetById(anyLong())).thenReturn(Optional.of(planet));

        Optional<PlanetResponse> actual = planetUseCase.getPlanetById(1);

        PlanetResponse expected = new PlanetResponse("Tatooine", "Arid", "Desert", 3);

        assertThat(actual.isPresent()).isTrue();
        assertThat(expected).isEqualTo(actual.get());
    }

    @Test
    public void shouldGetEmptyIfDontFind() {
        FakePlanetDataSource planetDataSource = mock(FakePlanetDataSource.class);
        StarWarApiService starWarApiService = mock(StarWarApiService.class);
        PlanetUseCase planetUseCase = new PlanetUseCase(planetDataSource, starWarApiService);

        when(planetDataSource.getPlanetById(anyLong())).thenReturn(Optional.empty());

        Optional<PlanetResponse> actual = planetUseCase.getPlanetById(1);

        assertThat(actual.isPresent()).isFalse();
    }
}
