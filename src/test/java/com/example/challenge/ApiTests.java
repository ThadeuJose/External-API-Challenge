package com.example.challenge;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;

public class ApiTests {
    @Test
    public void shouldReturnAmountOfCameo() {
        StarWarApiService starWarApiService = new StarWarApiService();
        Optional<Integer> amountCameo = starWarApiService.getAmountCameo("Tatooine");
        assertThat(amountCameo.isPresent()).isTrue();
        assertThat(amountCameo.get()).isEqualTo(5);
    }

    @Test
    public void shouldReturnOptionalEmptyIfDontHaveCameo() {
        StarWarApiService starWarApiService = new StarWarApiService();
        Optional<Integer> amountCameo = starWarApiService.getAmountCameo("7");
        assertThat(amountCameo.isPresent()).isFalse();
    }
}
