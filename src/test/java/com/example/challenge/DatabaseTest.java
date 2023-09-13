package com.example.challenge;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import com.example.challenge.database.PostgreePlanetDataSource;
import com.example.challenge.planet.PlanetDataModel;

@DataJpaTest
@Import(AppConfig.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class DatabaseTest {

    @Autowired
    PostgreePlanetDataSource dataSource;

    @Test
    void shouldSaveAndRetrieveEntityFromDatabaseByName() {
        dataSource.save(new PlanetDataModel("Test", "climate", "terrain", 2));
        List<PlanetDataModel> planetsByName = dataSource.getPlanetsByName("Test");
        assertThat(planetsByName).hasSize(1);
        assertThat(planetsByName.get(0).getName()).isEqualTo("Test");
    }

    @Test
    void shouldSaveAndRetrieveEntityFromDatabaseById() {
        int id = dataSource.save(new PlanetDataModel("Test 2", "climate", "terrain", 2));
        Optional<PlanetDataModel> planetById = dataSource.getPlanetById((long) id);
        assertThat(planetById.isPresent()).isTrue();
        assertThat(planetById.get().getName()).isEqualTo("Test 2");
    }

    @Test
    void shouldDeleteById() {
        int id = dataSource.save(new PlanetDataModel("Test 3", "climate", "terrain", 2));
        dataSource.save(new PlanetDataModel("Test 4", "climate", "terrain", 2));
        dataSource.save(new PlanetDataModel("Test 5", "climate", "terrain", 2));
        assertThat(dataSource.getAllPlanets()).hasSize(3);
        dataSource.removeById((long) id);
        assertThat(dataSource.getAllPlanets()).hasSize(2);
        assertThat(dataSource.getPlanetById((long) id).isPresent()).isFalse();
    }

}
