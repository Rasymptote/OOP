package ru.nsu.babich;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.nsu.babich.dto.BakerDto;
import ru.nsu.babich.dto.CourierDto;
import ru.nsu.babich.dto.PizzeriaSimulatorDto;

class PizzeriaSimulatorFactoryTest {

    @ParameterizedTest
    @MethodSource
    void checkCreatePizzeriaSimulator(PizzeriaSimulatorDto dto) {
        PizzeriaSimulator simulator = PizzeriaSimulator.createPizzeriaSimulator(dto);
        assertNotNull(simulator);
    }

    static Stream<PizzeriaSimulatorDto> checkCreatePizzeriaSimulator() {
        return Stream.of(
                new PizzeriaSimulatorDto(1, 1,
                        List.of(
                        new BakerDto(1, 1)
                ),
                        List.of(
                                new CourierDto(1, 1, 1)
                )
                ),
                new PizzeriaSimulatorDto(2, 3,
                        List.of(
                        new BakerDto(1, 1),
                        new BakerDto(2, 2)
                ),
                        List.of(
                                new CourierDto(1, 1, 1),
                                new CourierDto(2, 2, 2),
                                new CourierDto(3, 3, 3))
                )
        );
    }
}
