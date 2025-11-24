package ru.nsu.babich.student;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class StudentTest {

    @ParameterizedTest
    @MethodSource("provideTestData")
    void checkGetFundingType(String name, String surname, FundingType fundingType) {
        var student = new Student(name, surname, fundingType);
        assertEquals(fundingType, student.getFundingType());
    }

    @ParameterizedTest
    @MethodSource("provideTestData")
    void checkToString(String name, String surname, FundingType fundingType) {
        var student = new Student(name, surname, fundingType);
        assertEquals(String.format("%s %s", name, surname), student.toString());
    }

    static Stream<Arguments> provideTestData() {
        return Stream.of(
                Arguments.of("Ivan", "Ivanov", FundingType.SELF_FUNDED),
                Arguments.of("Anna", "Ivanova", FundingType.STATE_FUNDED)
        );
    }
}