package com.example.challenge.planet;

public class PlanetRequest {

    private String name;
    private String climate;
    private String terrain;

    public PlanetRequest(String name, String climate, String terrain) {
        this.name = name;
        this.climate = climate;
        this.terrain = terrain;
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

}
