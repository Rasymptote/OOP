package ru.nsu.babich.student;

public enum FundingType {
    STATE_FUNDED("Бюджет"),
    SELF_FUNDED("Платная");

    private final String fundingType;

    FundingType(String fundingType) {
        this.fundingType = fundingType;
    }

    @Override
    public String toString() {
        return fundingType;
    }
}
