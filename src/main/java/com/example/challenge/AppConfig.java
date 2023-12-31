package com.example.challenge;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.challenge.database.PlanetRepository;
import com.example.challenge.database.PostgreePlanetDataSource;
import com.example.challenge.planet.PlanetDataSource;
import com.example.challenge.planet.PlanetService;
import com.example.challenge.planet.PlanetUseCase;

@Configuration
public class AppConfig {
    @Bean
    public StarWarApiService starWarApiService() {
        return new StarWarApiService();
    }

    @Bean
    public PlanetDataSource planetDataSource(PlanetRepository repository) {
        return new PostgreePlanetDataSource(repository);
    }

    @Bean
    public PlanetUseCase planetUseCase(PlanetDataSource planetDataSource, StarWarApiService starWarApiService) {
        return new PlanetUseCase(planetDataSource, starWarApiService);
    }

    @Bean
    public PlanetService planetService(PlanetUseCase planetUseCase) {
        return new PlanetService(planetUseCase);
    }
}
