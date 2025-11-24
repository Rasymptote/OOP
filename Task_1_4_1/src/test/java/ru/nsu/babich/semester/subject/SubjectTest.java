package ru.nsu.babich.semester.subject;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class SubjectTest {

    @ParameterizedTest
    @MethodSource
    void checkCreateValidSubject(String name, Grade grade, AssessmentType assessmentType) {
       var subject = assertDoesNotThrow(() -> Subject.createSubject(name, grade, assessmentType));
        assertEquals(name, subject.getName());
        assertEquals(grade, subject.getGrade());
        assertEquals(assessmentType, subject.getAssessmentType());
    }

    static Stream<Arguments> checkCreateValidSubject() {
        return Stream.of(
                Arguments.of("Math analysis", Grade.EXCELLENT, AssessmentType.EXAM),
                Arguments.of("Discrete math", Grade.GOOD, AssessmentType.EXAM),
                Arguments.of("Imperative programming", Grade.SATISFACTORY, AssessmentType.GRADED_PASS),
                Arguments.of("Declarative programming", Grade.UNSATISFACTORY, AssessmentType.GRADED_PASS),
                Arguments.of("PE", Grade.PASS, AssessmentType.PASS),
                Arguments.of("Digital platforms", Grade.FAIL, AssessmentType.PASS),
                Arguments.of("Final thesis defense", Grade.EXCELLENT, AssessmentType.FINAL_THESIS_DEFENSE)
        );
    }

    @ParameterizedTest
    @MethodSource
    void checkCreateInvalidSubject(String name, Grade grade, AssessmentType assessmentType) {
        assertThrows(IllegalArgumentException.class,
                () -> Subject.createSubject(name, grade, assessmentType));
    }

    static Stream<Arguments> checkCreateInvalidSubject() {
        return Stream.of(
                Arguments.of("Math analysis", Grade.EXCELLENT, AssessmentType.PASS),
                Arguments.of("Discrete math", Grade.GOOD, AssessmentType.PASS)
        );
    }
}