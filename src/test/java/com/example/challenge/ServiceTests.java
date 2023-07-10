package com.example.challenge;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.example.challenge.planet.PlanetDataModel;
import com.example.challenge.planet.PlanetDataSource;
import com.example.challenge.planet.PlanetRequest;
import com.example.challenge.planet.PlanetUseCase;

public class ServiceTests {

    @Test
    public void shouldReturnId() {
        PlanetDataSource planetDataSource = mock(PlanetDataSource.class);
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
        PlanetDataSource planetDataSource = mock(PlanetDataSource.class);
        StarWarApiService starWarApiService = mock(StarWarApiService.class);
        PlanetUseCase planetUseCase = new PlanetUseCase(planetDataSource, starWarApiService);
        when(starWarApiService.getAmountCameo(any())).thenReturn(Optional.empty());
        when(planetDataSource.save(any())).thenReturn(2);

        Optional<Integer> id = planetUseCase.createPlanet(new PlanetRequest("TestName", "TestClimate", "TestTerrain"));

        assertThat(id.isPresent()).isFalse();
    }

    @Test
    public void shouldNotSaveIfPlanetIsNotFound() {
        PlanetDataSource planetDataSource = mock(PlanetDataSource.class);
        StarWarApiService starWarApiService = mock(StarWarApiService.class);
        PlanetUseCase planetUseCase = new PlanetUseCase(planetDataSource, starWarApiService);
        when(starWarApiService.getAmountCameo(any())).thenReturn(Optional.empty());
        when(planetDataSource.save(any())).thenReturn(2);

        planetUseCase.createPlanet(new PlanetRequest("TestName", "TestClimate", "TestTerrain"));

        verifyNoInteractions(planetDataSource);
    }
}
