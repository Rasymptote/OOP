package ru.nsu.babich;

/**
 * The enum of card ranks.
 */
public enum CardRank {
    TWO("Двойка", 2),
    THREE("Тройка", 3),
    FOUR("Четверка", 4),
    FIVE("Пятерка", 5),
    SIX("Шестерка", 6),
    SEVEN("Семерка", 7),
    EIGHT("Восьмерка", 8),
    NINE("Девятка", 9),
    TEN("Десятка", 10),
    JACK("Валет", 10),
    QUEEN("Дама", 10),
    KING("Король", 10),
    ACE("Туз", 11);

    private final String cardname;
    private final int cost;

    CardRank(String cardname, int cost) {
        this.cardname = cardname;
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return cardname;
    }

}


