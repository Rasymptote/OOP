package ru.nsu.babich.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Data Transfer Object representing a courier in the pizzeria.
 */
public record CourierDto(

        @JsonProperty(value = "id", required = true)
        int id,

        @JsonProperty(value = "trunkCapacity", required = true)
        int trunkCapacity,

        @JsonProperty(value = "deliverySpeedMs", required = true)
        int deliverySpeed
) {
}
