package com.example.challenge;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.challenge.planet.PlanetController;
import com.example.challenge.planet.PlanetRequest;
import com.example.challenge.planet.PlanetService;
import com.example.challenge.planet.PlanetUseCase;
import com.google.gson.Gson;

@WebMvcTest(PlanetController.class)
public class EndpointTest {

    private static final String APPLICATION_JSON = "application/json;charset=UTF-8";

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
    public void shouldCreatePlanetAndReturnCorrectUrl() throws Exception {
        when(planetUseCase.createPlanet(any())).thenReturn(Optional.of(3));

        Gson gson = new Gson();
        String json = gson.toJson(new PlanetRequest("TestName", "TestClima", "TestTerreno"));

        this.mockMvc.perform(post("/planets").contentType(APPLICATION_JSON).content(json).accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", containsString("/3")));
    }

    @Test
    public void shouldReturnErrorIfPlanetisNotFound() throws Exception {
        when(planetUseCase.createPlanet(any())).thenReturn(Optional.empty());

        Gson gson = new Gson();
        String json = gson.toJson(new PlanetRequest("7", "TestClima","TestTerreno"));

        String message = this.mockMvc.perform(post("/planets").contentType(APPLICATION_JSON).content(json).accept(APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isBadGateway())
        .andReturn().getResponse().getContentAsString();

        assertThat(message).contains("Can't find planet with name 7");        
    }

}
