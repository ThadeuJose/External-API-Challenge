package com.example.challenge;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.challenge.planet.PlanetDataSource;
import com.example.challenge.planet.PlanetService;

@Configuration
public class AppConfig {
    @Bean
    public StarWarApiService starWarApiService() {
        return new StarWarApiService();
    }

    @Bean
    public PlanetDataSource planetDataSource() {
        return new PlanetDataSource();
    }

    @Bean
    public PlanetService planetService(PlanetDataSource planetDataSource, StarWarApiService starWarApiService) {
        return new PlanetService(planetDataSource, starWarApiService);
    }
}
