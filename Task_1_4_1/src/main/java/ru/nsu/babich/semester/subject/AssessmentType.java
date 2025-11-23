package ru.nsu.babich.semester.subject;

public enum AssessmentType {
    EXAM("Экзамен"),
    GRADED_PASS("Дифференцированный зачет"),
    PASS("Зачет"),
    FINAL_THESIS_DEFENSE("Защита ВКР");

    private final String assessment;

    AssessmentType(String assessment) {
        this.assessment = assessment;
    }

    @Override
    public String toString() {
        return assessment;
    }
}
