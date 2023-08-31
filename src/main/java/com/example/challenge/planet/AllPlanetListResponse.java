package com.example.challenge.planet;

import java.util.List;
import java.util.Objects;

public class AllPlanetListResponse {
    private int total;
    private List<PlanetResponse> planets;

    public AllPlanetListResponse(int total, List<PlanetResponse> planets) {
        this.total = total;
        this.planets = planets;
    }

    public int getTotal() {
        return total;
    }

    public List<PlanetResponse> getPlanets() {
        return planets;
    }

    @Override
    public int hashCode() {
        return Objects.hash(total, planets);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AllPlanetListResponse other = (AllPlanetListResponse) obj;
        return total == other.total && Objects.equals(planets, other.planets);
    }

}
