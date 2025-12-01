package ru.nsu.babich.semester;

import java.util.ArrayList;
import java.util.List;
import ru.nsu.babich.semester.subject.Subject;

/**
 * Represents an academic semester containing a list of subjects.
 */
public class Semester {
    private final List<Subject> subjects;
    private final int number;

    /**
     * Constructs a new semester with the specified number.
     *
     * @param number The semester number (1-8)
     */
    public Semester(int number) {
        this.subjects = new ArrayList<>();
        this.number = number;
    }

    /**
     * Adds a subject to the semester.
     *
     * @param subject The subject to add.
     */
    public void addSubject(Subject subject) {
        subjects.add(subject);
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        var builder = new StringBuilder();
        builder.append(String.format("%d семестр\n", number));
        builder.append("Дисциплина/Вид контроля/Оценка\n");
        for (var subject : subjects) {
            builder.append(String.format("%s/%s/%s\n", subject.getName(), subject.getAssessmentType(),
                    subject.getGrade()));
        }
        return builder.toString();
    }
}
