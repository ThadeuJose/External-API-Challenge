package com.example.challenge;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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

@WebMvcTest(PlanetController.class)
public class DeleteEndpointTest {

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
    public void shouldReturn404IfNotFound() throws Exception {
        when(planetUseCase.getPlanetById(anyLong())).thenReturn(Optional.empty());

        this.mockMvc.perform(delete("/planets/1"))
                .andDo(print())
                .andExpect(status().isNotFound());
                
    }

    @Test
    public void shouldDelete() throws Exception {
        when(planetUseCase.getPlanetById(anyLong())).thenReturn(Optional.of(new PlanetResponse("Test 1", "climate", "terrain", 3)));

        this.mockMvc.perform(delete("/planets/1"))
                .andDo(print())
                .andExpect(status().isOk());
        
         verify(planetUseCase).deletePlanetById(anyLong());
    }

}
