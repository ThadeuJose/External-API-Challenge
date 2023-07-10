package com.example.challenge;

import java.util.Optional;

import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;

public class StarWarApiService {

    public Optional<Integer> getAmountCameo(String planetName) {
        String url = "https://swapi.dev/api/planets/";

        JSONObject object = Unirest.get(url)
                .queryString("search", planetName)
                .asJson()
                .getBody()
                .getObject();

        int count = object.getInt("count");
        if (count >= 1) {
            int result = object
                    .getJSONArray("results")
                    .getJSONObject(0)
                    .getJSONArray("films")
                    .length();

            return Optional.of(result);
        }
        return Optional.empty();
    }

}
