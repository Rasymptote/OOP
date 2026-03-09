package ru.nsu.babich.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * Data Transfer Object representing the configuration of the pizzeria simulator.
 */
public record PizzeriaSimulatorDto(

        @JsonProperty(value = "workTimeMs", required = true)
        int workTime,

        @JsonProperty(value = "storageCapacity", required = true)
        int storageCapacity,

        @JsonProperty(value = "bakers", required = true)
        List<BakerDto> bakers,

        @JsonProperty(value = "couriers", required = true)
        List<CourierDto> couriers
) {
}
