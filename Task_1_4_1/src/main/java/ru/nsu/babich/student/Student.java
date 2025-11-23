package ru.nsu.babich.student;

public class Student {
    private final String name;
    private final String surname;
    private final FundingType fundingType;

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
