package ru.nsu.babich.semester;

import java.util.ArrayList;
import java.util.List;
import ru.nsu.babich.semester.subject.Subject;

public class Semester {
    private final List<Subject> subjects;
    private final int number;

    public Semester(int number) {
        this.subjects = new ArrayList<>();
        this.number = number;
    }

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
