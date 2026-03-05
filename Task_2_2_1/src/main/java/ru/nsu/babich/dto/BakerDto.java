package ru.nsu.babich.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record BakerDto(

        @JsonProperty("id")
        int id,

        @JsonProperty("cookingSpeedMs")
        int cookingSpeed
) {
}
