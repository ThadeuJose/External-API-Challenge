package com.example.challenge.planet;

import java.util.Objects;

public class PlanetResponse {
    private String name;
    private String climate;
    private String terrain;
    private int amountCameo;

    public PlanetResponse(String name, String climate, String terrain, int amountCameo) {
        this.name = name;
        this.climate = climate;
        this.terrain = terrain;
        this.amountCameo = amountCameo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, climate, terrain, amountCameo);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PlanetResponse other = (PlanetResponse) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (climate == null) {
            if (other.climate != null)
                return false;
        } else if (!climate.equals(other.climate))
            return false;
        if (terrain == null) {
            if (other.terrain != null)
                return false;
        } else if (!terrain.equals(other.terrain))
            return false;
        if (amountCameo != other.amountCameo)
            return false;
        return true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClimate() {
        return climate;
    }

    public void setClimate(String climate) {
        this.climate = climate;
    }

    public String getTerrain() {
        return terrain;
    }

    public void setTerrain(String terrain) {
        this.terrain = terrain;
    }

    public int getAmountCameo() {
        return amountCameo;
    }

    public void setAmountCameo(int amountCameo) {
        this.amountCameo = amountCameo;
    }

}
