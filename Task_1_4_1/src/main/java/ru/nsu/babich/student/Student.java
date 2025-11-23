package ru.nsu.babich.student;

/**
 * Represents a student with personal information.
 */
public class Student {
    private final String name;
    private final String surname;
    private final FundingType fundingType;

    /**
     * Constructs a new student.
     *
     * @param name Student's first name.
     * @param surname Student's last name.
     * @param fundingType Type of education funding.
     */
    public Student(String name, String surname, FundingType fundingType) {
        this.name = name;
        this.surname = surname;
        this.fundingType = fundingType;
    }

    public FundingType getFundingType() {
        return fundingType;
    }

    @Override
    public String toString() {
        return String.format("%s %s", name, surname);
    }
}
