package ru.nsu.babich;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.nsu.babich.semester.subject.AssessmentType;
import ru.nsu.babich.semester.subject.Grade;
import ru.nsu.babich.semester.subject.Subject;
import ru.nsu.babich.student.FundingType;
import ru.nsu.babich.student.Student;

class GradeBookTest {
    private GradeBook book;

    @BeforeEach
    void setUp() {
        var selfFundedStudent = new Student("Ivan", "Ivanov", FundingType.SELF_FUNDED);
        book = new GradeBook(selfFundedStudent);
    }

    @ParameterizedTest
    @MethodSource
    void checkGetAverage(List<Subject> subjects, double expectedAverage) {
        for (var subject : subjects) {
            book.setSubjectGrade(1, subject);
        }
        assertEquals(expectedAverage, book.getAverage(), 0.01);
    }

    static Stream<Arguments> checkGetAverage() {
        return Stream.of(
                Arguments.of(
                        List.of(Subject.createSubject("Math analysis",
                                        Grade.EXCELLENT, AssessmentType.EXAM),
                                Subject.createSubject("Discrete math",
                                        Grade.GOOD, AssessmentType.EXAM),
                                Subject.createSubject("Digital platforms",
                                Grade.GOOD, AssessmentType.GRADED_PASS)), 4.33),
                Arguments.of(
                        List.of(Subject.createSubject("Differential equations",
                                        Grade.EXCELLENT, AssessmentType.EXAM),
                                Subject.createSubject("Computations",
                                        Grade.GOOD, AssessmentType.EXAM),
                                Subject.createSubject("PE",
                                Grade.PASS, AssessmentType.PASS)), 4.5),
                Arguments.of(
                        List.of(Subject.createSubject("Final thesis defense",
                                Grade.EXCELLENT, AssessmentType.FINAL_THESIS_DEFENSE)), 5.0)
        );
    }

    @ParameterizedTest
    @MethodSource
    void checkCanTransferToStateFunded(String message, List<Subject> subjects1,
                                       List<Subject> subjects2, boolean canTransfer) {
        for (var subject : subjects1) {
            book.setSubjectGrade(1, subject);
        }
        for (var subject : subjects2) {
            book.setSubjectGrade(2, subject);
        }
        if (!subjects2.isEmpty()) {
            book.setSubjectGrade(3, Subject.createSubject(" ",
                    Grade.SATISFACTORY, AssessmentType.GRADED_PASS));
        }
        assertEquals(canTransfer, book.canTransferToStateFunded(), message);
    }

    static Stream<Arguments> checkCanTransferToStateFunded() {
        return Stream.of(
                Arguments.of("First semester has excellent grades",
                        List.of(Subject.createSubject("Math analysis",
                                        Grade.EXCELLENT, AssessmentType.EXAM),
                                Subject.createSubject("Discrete math",
                                        Grade.EXCELLENT, AssessmentType.EXAM)),
                        List.of(Subject.createSubject("Digital platforms",
                                        Grade.GOOD, AssessmentType.GRADED_PASS)),
                        true
                ),
                Arguments.of("First semester has Satisfactory grade",
                        List.of(Subject.createSubject("Differential equations",
                                        Grade.EXCELLENT, AssessmentType.EXAM),
                                        Subject.createSubject("Computations",
                                        Grade.SATISFACTORY, AssessmentType.EXAM)),
                        List.of(Subject.createSubject("PE",
                                        Grade.PASS, AssessmentType.PASS),
                                Subject.createSubject("Physics",
                                        Grade.EXCELLENT, AssessmentType.EXAM)),
                        false
                ),
                Arguments.of("Semester number is less than 3",
                        List.of(Subject.createSubject("Math analysis",
                                Grade.EXCELLENT, AssessmentType.EXAM)),
                        List.of(),
                        false
                )
        );
    }

    @ParameterizedTest
    @MethodSource
    void checkCanGetRedDiploma(String message, int excellentCount, int goodCount,
                               int satisfactoryCount, Grade thesisGrade, boolean expected) {
        int counter = 0;
        for (int i = 0; i < excellentCount; i++) {
            book.setSubjectGrade(1, Subject.createSubject("Subject" + (counter++),
                    Grade.EXCELLENT, AssessmentType.EXAM));
        }
        for (int i = 0; i < goodCount; i++) {
            book.setSubjectGrade(1, Subject.createSubject("Subject" + (counter++),
                    Grade.GOOD, AssessmentType.EXAM));
        }
        for (int i = 0; i < satisfactoryCount; i++) {
            book.setSubjectGrade(1, Subject.createSubject("Subject" + (counter++),
                    Grade.SATISFACTORY, AssessmentType.EXAM));
        }
        book.setSubjectGrade(8, Subject.createSubject("Subject",
               thesisGrade, AssessmentType.FINAL_THESIS_DEFENSE));
        assertEquals(expected, book.canGetRedDiploma(), message);
    }

    static Stream<Arguments> checkCanGetRedDiploma() {
        return Stream.of(
                Arguments.of("75% excellent, no satisfactory, excellent thesis",
                        3, 1, 0, Grade.EXCELLENT, true
                ),
                Arguments.of("50% excellent",
                        2, 2, 0, Grade.EXCELLENT, false
                ),
                Arguments.of("Has satisfactory grade",
                        3, 0, 1, Grade.EXCELLENT, false
                ),
                Arguments.of("Thesis not excellent",
                        3, 1, 0, Grade.GOOD, false
                ),
                Arguments.of("100% excellent",
                        4, 0, 0, Grade.EXCELLENT, true
                )
        );
    }

    @ParameterizedTest
    @MethodSource
    void checkCanGetIncreasedScholarship(String message, List<Subject> subjects,
                                         boolean expected, boolean addCurrentSemester) {
        for (var subject : subjects) {
            book.setSubjectGrade(1, subject);
        }
        if (addCurrentSemester) {
            book.setSubjectGrade(2, Subject.createSubject("Subject",
                    Grade.PASS, AssessmentType.PASS));
        }
        assertEquals(expected, book.canGetIncreasedScholarship(), message);
    }

    static Stream<Arguments> checkCanGetIncreasedScholarship() {
        return Stream.of(
                Arguments.of("All excellent grades in previous semester",
                        List.of(Subject.createSubject("Math analysis",
                                        Grade.EXCELLENT, AssessmentType.EXAM),
                                Subject.createSubject("Discrete math",
                                        Grade.EXCELLENT, AssessmentType.EXAM),
                                Subject.createSubject("Digital platforms",
                                        Grade.EXCELLENT, AssessmentType.GRADED_PASS)),
                        true, true
                ),
                Arguments.of("One good grade prevents scholarship",
                        List.of(Subject.createSubject("Math analysis",
                                        Grade.EXCELLENT, AssessmentType.EXAM),
                                Subject.createSubject("Discrete math",
                                        Grade.GOOD, AssessmentType.EXAM),
                                Subject.createSubject("Digital platforms",
                                        Grade.EXCELLENT, AssessmentType.GRADED_PASS)),
                        false, true
                ),
                Arguments.of("Pass/fail subjects don't affect scholarship",
                        List.of(Subject.createSubject("Math analysis",
                                        Grade.EXCELLENT, AssessmentType.EXAM),
                                Subject.createSubject("PE",
                                        Grade.PASS, AssessmentType.PASS),
                                Subject.createSubject("Digital platforms",
                                        Grade.EXCELLENT, AssessmentType.GRADED_PASS)),
                        true, true
                ),
                Arguments.of("First semester - no previous semester for comparison",
                        List.of(Subject.createSubject("Math analysis",
                                Grade.EXCELLENT, AssessmentType.EXAM)),
                        false, false
                )
        );
    }
}