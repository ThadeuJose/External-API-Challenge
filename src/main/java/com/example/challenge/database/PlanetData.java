package com.example.challenge.database;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class PlanetData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String climate;

    private String terrain;

    private int amountCameo;

    public PlanetData() {
    }

    public PlanetData(String name, String climate, String terrain, int amountCameo) {
        this.name = name;
        this.climate = climate;
        this.terrain = terrain;
        this.amountCameo = amountCameo;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getClimate() {
        return climate;
    }

    public String getTerrain() {
        return terrain;
    }

    public int getAmountCameo() {
        return amountCameo;
    }

}
