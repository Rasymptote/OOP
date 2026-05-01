package ru.nsu.babich.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Data Transfer Object representing a baker in the pizzeria.
 */
public record BakerDto(

        @JsonProperty(value = "id", required = true)
        int id,

        @JsonProperty(value = "cookingSpeedMs", required = true)
        int cookingSpeed
) {
}
