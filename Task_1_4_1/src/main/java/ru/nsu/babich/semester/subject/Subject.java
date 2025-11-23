package ru.nsu.babich.semester.subject;

/**
 * Represents an academic subject with its grade and assessment type.
 */
public class Subject {
    private final String name;
    private final Grade grade;
    private final AssessmentType assessmentType;

    private Subject(String name, Grade grade, AssessmentType assessmentType) {
        this.name = name;
        this.grade = grade;
        this.assessmentType = assessmentType;
    }

    /**
     * Factory method for creating Subject instances with validation.
     *
     * @param name The name of the subject.
     * @param grade The grade received.
     * @param assessmentType The type of assessment.
     * @return New Subject instance.
     */
    public static Subject createSubject(String name, Grade grade, AssessmentType assessmentType) {

        switch (assessmentType) {
            case PASS:
                if (grade == Grade.PASS || grade == Grade.FAIL) {
                    return new Subject(name, grade, assessmentType);
                } else {
                    throw new IllegalArgumentException("Тип контроля \"" + assessmentType +
                            "\" поддерживает только оценку Зачет/Незачет");
                }
            default:
                if (grade != Grade.PASS && grade != Grade.FAIL) {
                    return new Subject(name, grade, assessmentType);
                } else {
                    throw new IllegalArgumentException("Тип контроля \"" + assessmentType +
                            "\" не поддерживает оценку Зачет/Незачет");
                }
        }
    }

    public String getName() {
        return name;
    }

    public Grade getGrade() {
        return grade;
    }

    public AssessmentType getAssessmentType() {
        return assessmentType;
    }
}
