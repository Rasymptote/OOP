package ru.nsu.babich.semester.subject;

/**
 * Represents possible grades including not numeric grades (pass/fail).
 */
public enum Grade {
    EXCELLENT("Отлично", 5),
    GOOD("Хорошо", 4),
    SATISFACTORY("Удовлетворительно", 3),
    UNSATISFACTORY("Неудовлетворительно", 2),
    PASS("Зачет"),
    FAIL("Незачет");

    private final String name;
    private final int value;

    Grade(String name) {
        this(name, -1);
    }

    Grade(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return name;
    }
}
