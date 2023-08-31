package com.example.challenge.planet;

import java.util.List;
import java.util.Objects;

public class QueriedPlanetListResponse {
    private int total;
    private List<PlanetResponse> planets;
    private String query;

    public QueriedPlanetListResponse(int total, List<PlanetResponse> planets, String query) {
        this.total = total;
        this.planets = planets;
        this.query = query;
    }

    public int getTotal() {
        return total;
    }

    public List<PlanetResponse> getPlanets() {
        return planets;
    }

    public String getQuery() {
        return query;
    }

    @Override
    public int hashCode() {
        return Objects.hash(total, planets, query);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        QueriedPlanetListResponse other = (QueriedPlanetListResponse) obj;
        return total == other.total && Objects.equals(planets, other.planets) && Objects.equals(query, other.query);
    }

}
