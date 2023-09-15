package com.example.challenge;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;

import com.example.challenge.planet.AllPlanetListResponse;
import com.example.challenge.planet.PlanetRequest;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class IntegrationTest {

	@Value(value = "${local.server.port}")
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void shouldCreateAndRemovePlanets() throws Exception {
		String URL = "http://localhost:" + port + "/planet";
		this.restTemplate.postForEntity(URL, createRequest(), String.class);
		this.restTemplate.postForEntity(URL, createRequest(), String.class);
		this.restTemplate.postForEntity(URL, createRequest(), String.class);

		AllPlanetListResponse response1 = this.restTemplate.getForObject("http://localhost:" + port + "/planets",
				AllPlanetListResponse.class);

		assertThat(response1.getTotal()).isEqualTo(3);

		this.restTemplate.delete("http://localhost:" + port + "/planets/1");

		AllPlanetListResponse response2 = this.restTemplate.getForObject("http://localhost:" + port + "/planets",
				AllPlanetListResponse.class);

		assertThat(response2.getTotal()).isEqualTo(2);
	}

	private HttpEntity<PlanetRequest> createRequest() {
		PlanetRequest body = new PlanetRequest("Tatooine", "Arid", "Desert");
		return new HttpEntity<PlanetRequest>(body);
	}
}