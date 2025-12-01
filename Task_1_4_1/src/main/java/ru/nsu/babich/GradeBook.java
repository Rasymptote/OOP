package ru.nsu.babich;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ru.nsu.babich.semester.Semester;
import ru.nsu.babich.semester.subject.AssessmentType;
import ru.nsu.babich.semester.subject.Grade;
import ru.nsu.babich.semester.subject.Subject;
import ru.nsu.babich.student.FundingType;
import ru.nsu.babich.student.Student;

/**
 * Represents an electronic grade book.
 */
public class GradeBook {
    private final Student student;
    private final List<Semester> semesters;

    /**
     * Constructs a new grade book for the specified student.
     *
     * @param student The student who owns this grade book.
     */
    public GradeBook(Student student) {
        this.student = student;
        this.semesters = new ArrayList<>();

        for (int i = 1; i <= 8; i++) {
            semesters.add(new Semester(i));
        }
    }

    /**
     * Adds a subject with grade to the specified semester.
     *
     * @param semesterNumber The semester number (1-8).
     * @param subject The subject with grade to add.
     */
    public void setSubjectGrade(int semesterNumber, Subject subject) {
        if (semesterNumber > 8 || semesterNumber < 1) {
            throw new IllegalArgumentException();
        }
        var semester = semesters.get(semesterNumber - 1);
        semester.addSubject(subject);
    }

    /**
     * Calculates average grade.
     *
     * @return Average grade value.
     */
    public double getAverage() {
        return semesters.stream()
                .flatMap(semester -> semester.getSubjects().stream())
                .filter(subject -> subject.getAssessmentType() != AssessmentType.PASS)
                .mapToInt(subject -> subject.getGrade().getValue())
                .average()
                .orElse(0.0);
    }

    /**
     * Checks if the student is eligible to transfer from self-funded to state-funded education.
     *
     * @return {@code true} if eligible to transfer, {@code false} otherwise.
     */
    public boolean canTransferToStateFunded() {
        if (student.getFundingType() == FundingType.STATE_FUNDED) {
            return false;
        }
        int currentSemester = getCurrentSemester();
        if (currentSemester <= 2) {
            return false;
        }
        return semesters.stream()
                .filter(semester -> semester.getNumber() == currentSemester - 1 ||
                        semester.getNumber() == currentSemester - 2)
                .flatMap(semester -> semester.getSubjects().stream())
                .filter(subject -> subject.getAssessmentType() == AssessmentType.EXAM)
                .allMatch(subject -> subject.getGrade().getValue() > Grade.SATISFACTORY.getValue());
    }

    /**
     * Checks if the student can get red diploma.
     *
     * @return {@code true} if can, {@code false} otherwise.
     */
    public boolean canGetRedDiploma() {
        var gradedSubjects = getLatestSubjects().stream()
                .filter(subject -> subject.getAssessmentType() != AssessmentType.PASS)
                .toList();
        return gradedSubjects.stream()
                .filter(subject -> subject.getGrade().getValue() == Grade.EXCELLENT.getValue())
                .count() / (double) gradedSubjects.size() >= 0.75
                && gradedSubjects.stream()
                .allMatch(subject -> subject.getGrade().getValue() > Grade.SATISFACTORY.getValue())
                && gradedSubjects.stream()
                .filter(subject -> subject.getAssessmentType() == AssessmentType.FINAL_THESIS_DEFENSE)
                .findFirst()
                .orElse(Subject.createSubject("ВКР", Grade.EXCELLENT, AssessmentType.FINAL_THESIS_DEFENSE))
                .getGrade() == Grade.EXCELLENT;
    }

    /**
     * Checks if the student is eligible for increased scholarship in the current semester.
     *
     * @return {@code true} if eligible, {@code false} otherwise.
     */
    public boolean canGetIncreasedScholarship() {
        int currentSemester = getCurrentSemester();
        if (currentSemester == 1) {
            return false;
        }
        return semesters.stream()
                .filter(semester -> semester.getNumber() == currentSemester - 1)
                .flatMap(semester -> semester.getSubjects().stream())
                .filter(subject -> subject.getAssessmentType() != AssessmentType.PASS)
                .allMatch(subject -> subject.getGrade() == Grade.EXCELLENT);
    }

    @Override
    public String toString() {
        var builder = new StringBuilder();
        builder.append("Зачетная книжка\n");
        builder.append("-------------------\n");
        builder.append(student).append("\n");
        builder.append("Форма обучения: ").append(student.getFundingType()).append("\n");
        builder.append("-------------------\n");
        for (var semester : semesters) {
            builder.append(semester).append("\n");
            builder.append("-------------------\n");
        }
        return builder.toString();
    }

    private int getCurrentSemester() {
        return semesters.stream()
                .filter(semester -> !semester.getSubjects().isEmpty())
                .mapToInt(Semester::getNumber)
                .max()
                .orElse(1);
    }

    private List<Subject> getLatestSubjects() {
        Map<String, Subject> latestSubjects = new HashMap<>();

        for (var semester : semesters) {
            for (var subject : semester.getSubjects()) {
                latestSubjects.put(subject.getName(), subject);
            }
        }
        return new ArrayList<>(latestSubjects.values());
    }
}
