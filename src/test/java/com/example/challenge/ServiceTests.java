package com.example.challenge;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.example.challenge.planet.PlanetDataModel;
import com.example.challenge.planet.PlanetDataSource;
import com.example.challenge.planet.PlanetRequest;
import com.example.challenge.planet.PlanetService;

public class ServiceTests {

    @Test
    public void shouldReturnId() {
        PlanetDataSource planetDataSource = mock(PlanetDataSource.class);
        StarWarApiService starWarApiService = mock(StarWarApiService.class);
        PlanetService planetService = new PlanetService(planetDataSource, starWarApiService);
        when(starWarApiService.getAmountCameo(any())).thenReturn(Optional.of(1));
        when(planetDataSource.save(any())).thenReturn(2);

        int id = planetService.createPlanet(new PlanetRequest("TestName", "TestClimate", "TestTerrain"));

        assertThat(id).isEqualTo(2);
        verify(planetDataSource).save(new PlanetDataModel("TestName", "TestClimate", "TestTerrain", 1));
    }
}
