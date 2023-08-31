package com.example.challenge;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

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
import com.example.challenge.planet.QueriedPlanetListResponse;
import com.google.gson.Gson;

@WebMvcTest(PlanetController.class)
public class PlanetByNameEndpointTest {

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
    public void shouldReturnPlanetsByName() throws Exception {
        PlanetResponse planets = new PlanetResponse("Tatooine", "Arid", "Desert", 3);

        when(planetUseCase.getPlanetsByName("Tatooine")).thenReturn(List.of(planets));

        String json = this.mockMvc.perform(get("/planets?name=Tatooine"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        Gson gson = new Gson();
        QueriedPlanetListResponse actual = gson.fromJson(json, QueriedPlanetListResponse.class);

        QueriedPlanetListResponse expected = new QueriedPlanetListResponse(1,
                List.of(new PlanetResponse("Tatooine", "Arid", "Desert", 3)), "Tatooine");

        assertThat(actual).isEqualTo(expected);
    }
}
