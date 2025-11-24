package ru.nsu.babich.semester;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.nsu.babich.semester.subject.AssessmentType;
import ru.nsu.babich.semester.subject.Grade;
import ru.nsu.babich.semester.subject.Subject;

class SemesterTest {

    @ParameterizedTest
    @MethodSource("provideTestData")
    void checkAddSubjects(int number, List<Subject> subjects) {
        Semester semester = new Semester(number);
        for (var subject : subjects) {
            semester.addSubject(subject);
        }
        assertEquals(number, semester.getNumber());
        assertEquals(subjects, semester.getSubjects());
    }


    @ParameterizedTest
    @MethodSource("provideTestData")
    void checkToString(int number, List<Subject> subjects) {
        var semester = new Semester(number);
        assertTrue(semester.toString().contains(String.format("%d семестр", number)));
        for (var subject : subjects) {
           semester.addSubject(subject);
           assertTrue(semester.toString().contains(String.format("%s/%s/%s", subject.getName(),
                   subject.getAssessmentType(), subject.getGrade())));
        }
    }

    static Stream<Arguments> provideTestData() {
        return Stream.of(
                Arguments.of(1,
                        List.of(Subject.createSubject("Math analysis",
                                        Grade.EXCELLENT, AssessmentType.EXAM),
                                Subject.createSubject("Discrete math",
                                        Grade.GOOD, AssessmentType.EXAM)),
                                Subject.createSubject("Digital platforms",
                                        Grade.GOOD, AssessmentType.GRADED_PASS)),
                Arguments.of(3,
                        List.of(Subject.createSubject("Differential equations",
                                Grade.EXCELLENT, AssessmentType.EXAM),
                                Subject.createSubject("Computations",
                                Grade.GOOD, AssessmentType.EXAM)),
                                Subject.createSubject("PE",
                                Grade.PASS, AssessmentType.PASS)),
                Arguments.of(8,
                        List.of(Subject.createSubject("Final thesis defense",
                                Grade.EXCELLENT, AssessmentType.FINAL_THESIS_DEFENSE)))
        );
    }
}
