package ru.nsu.babich.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Data Transfer Object representing a baker in the pizzeria.
 */
public record BakerDto(

        @JsonProperty("id")
        int id,

        @JsonProperty("cookingSpeedMs")
        int cookingSpeed
) {
}
