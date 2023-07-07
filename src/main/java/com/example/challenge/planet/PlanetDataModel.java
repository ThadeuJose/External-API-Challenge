package com.example.challenge.planet;

import java.util.Objects;

public class PlanetDataModel {
    private String name;
    private String climate;
    private String terrain;
    private int amountCameo;

    public PlanetDataModel(String name, String climate, String terrain, int amountCameo) {
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
        PlanetDataModel other = (PlanetDataModel) obj;
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

}
