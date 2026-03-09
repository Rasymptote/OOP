package ru.nsu.babich.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Data Transfer Object representing a courier in the pizzeria.
 */
public record CourierDto(

        @JsonProperty("id")
        int id,

        @JsonProperty("trunkCapacity")
        int trunkCapacity,

        @JsonProperty("deliverySpeedMs")
        int deliverySpeed
) {
}
