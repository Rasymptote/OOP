package ru.nsu.babich.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * Data Transfer Object representing the configuration of the pizzeria simulator.
 */
public record PizzeriaSimulatorDto(

        @JsonProperty("workTimeMs")
        int workTime,

        @JsonProperty("storageCapacity")
        int storageCapacity,

        @JsonProperty("bakers")
        List<BakerDto> bakers,

        @JsonProperty("couriers")
        List<CourierDto> couriers
) {
}
