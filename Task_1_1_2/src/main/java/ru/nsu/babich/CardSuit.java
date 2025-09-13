package ru.nsu.babich;

public enum CardSuit {
    DIAMONDS ("Бубны"),
    HEARTS ("Червы"),
    SPADES ("Пики"),
    CLUBS ("Трефы");

    private final String suit;

    CardSuit(String suit) {
        this.suit = suit;
    }

    @Override
    public String toString() {
        return suit;
    }
}
