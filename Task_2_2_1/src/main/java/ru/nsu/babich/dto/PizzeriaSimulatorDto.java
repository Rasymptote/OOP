package ru.nsu.babich.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record PizzeriaSimulatorDto(

        @JsonProperty("workTimeSeconds")
        int workTime,

        @JsonProperty("storageCapacity")
        int storageCapacity,

        @JsonProperty("bakers")
        List<BakerDto> bakers,

        @JsonProperty("couriers")
        List<CourierDto> couriers
) {
}
