package com.example.challenge;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.challenge.planet.PlanetController;
import com.example.challenge.planet.PlanetRequest;
import com.example.challenge.planet.PlanetService;
import com.google.gson.Gson;

@WebMvcTest(PlanetController.class)
public class IntegrationTests {

    private static final String APPLICATION_JSON = "application/json;charset=UTF-8";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    PlanetService planetService;

    @Test
    public void shouldCreatePlanetAndReturnCorrectUrl() throws Exception {
        when(planetService.createPlanet(any())).thenReturn(3);

        Gson gson = new Gson();
        String json = gson.toJson(new PlanetRequest("TestName", "TestClima", "TestTerreno"));

        this.mockMvc.perform(post("/planet").contentType(APPLICATION_JSON).content(json).accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", containsString("/3")));
    }
}
