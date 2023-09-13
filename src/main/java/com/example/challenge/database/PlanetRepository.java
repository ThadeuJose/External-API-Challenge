package com.example.challenge.database;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanetRepository extends JpaRepository<PlanetData, Long> {
    List<PlanetData> findByNameContainingIgnoreCase(String name);
}
