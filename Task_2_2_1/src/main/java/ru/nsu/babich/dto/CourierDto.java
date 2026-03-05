package ru.nsu.babich.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CourierDto(

        @JsonProperty("id")
        int id,

        @JsonProperty("trunkCapacity")
        int trunkCapacity,

        @JsonProperty("deliverySpeedMs")
        int deliverySpeed
) {
}
