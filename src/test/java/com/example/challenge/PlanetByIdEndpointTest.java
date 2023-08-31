package com.example.challenge;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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
import com.example.challenge.planet.PlanetResponse;
import com.example.challenge.planet.PlanetService;
import com.example.challenge.planet.PlanetUseCase;
import com.google.gson.Gson;

@WebMvcTest(PlanetController.class)
public class PlanetByIdEndpointTest {

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
    public void shouldReturnNotFoundIfDontHaveId() throws Exception {
        when(planetUseCase.getPlanetById(anyLong())).thenReturn(Optional.empty());

        this.mockMvc.perform(get("/planets/1"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldReturnPlanetById() throws Exception {
        PlanetResponse planets = new PlanetResponse("Tatooine", "Arid", "Desert", 3);

        when(planetUseCase.getPlanetById(1)).thenReturn(Optional.of(planets));

        String json = this.mockMvc.perform(get("/planets/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        Gson gson = new Gson();
        PlanetResponse actual = gson.fromJson(json, PlanetResponse.class);

        PlanetResponse expected = new PlanetResponse("Tatooine", "Arid", "Desert", 3);

        assertThat(actual).isEqualTo(expected);
    }
}
