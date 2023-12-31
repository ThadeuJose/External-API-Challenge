package com.example.challenge;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.challenge.planet.AllPlanetListResponse;
import com.example.challenge.planet.PlanetController;
import com.example.challenge.planet.PlanetResponse;
import com.example.challenge.planet.PlanetService;
import com.example.challenge.planet.PlanetUseCase;
import com.google.gson.Gson;

@WebMvcTest(PlanetController.class)
public class ListPlanetEndpointTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    PlanetUseCase planetUseCase;

    @TestConfiguration
    public static class TestConfig {
        @Bean
        public PlanetService testHelper(PlanetUseCase planetUseCase) {
            return new PlanetService(planetUseCase);
        }
    }

    @Test
    public void shouldReturnListOfPlanet() throws Exception {
        List<PlanetResponse> planets = new ArrayList<>();
        planets.add(new PlanetResponse("Tatooine", "Arid", "Desert", 3));
        planets.add(new PlanetResponse("Hoth", "Frozen", "Ice plains", 4));
        planets.add(new PlanetResponse("Endor", "Temperate", "Forested moon", 3));

        when(planetUseCase.getAllPlanets()).thenReturn(planets);

        String json = this.mockMvc.perform(get("/planets"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        Gson gson = new Gson();
        AllPlanetListResponse expected = gson.fromJson(json, AllPlanetListResponse.class);

        List<PlanetResponse> planetsInResponse = new ArrayList<>();
        planetsInResponse.add(new PlanetResponse("Tatooine", "Arid", "Desert", 3));
        planetsInResponse.add(new PlanetResponse("Hoth", "Frozen", "Ice plains", 4));
        planetsInResponse.add(new PlanetResponse("Endor", "Temperate", "Forested moon", 3));

        AllPlanetListResponse actual = new AllPlanetListResponse(planetsInResponse.size(), planetsInResponse);

        assertThat(expected).isEqualTo(actual);
    }
}
