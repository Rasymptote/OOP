package ru.nsu.babich;

/**
 * Class represents a playing card.
 * A card has a rank (value), a suit, and a state (hidden/unhidden).
 */
public class Card {
    public final CardRank rank;
    public final CardSuit suit;
    private boolean hidden;

    /**
     * Constructs a card with specified rank and suit.
     * By default, the card is unhidden.
     *
     * @param rank the rank of the card
     * @param suit the suit of the card
     */
    public Card(CardRank rank, CardSuit suit) {
        this.rank = rank;
        this.suit = suit;
        this.hidden = false;
    }

    /**
     * Constructs a card with specified rank, suit and visibility state.
     * Used when dealing cards that should be hidden.
     *
     * @param rank the rank of the card
     * @param suit the suit of the card
     * @param hidden whether the card should be dealt hidden
     */
    public Card(CardRank rank, CardSuit suit, boolean hidden) {
        this.rank = rank;
        this.suit = suit;
        this.hidden = hidden;
    }

    /**
     * Returns the Blackjack value (cost) of the card.
     *
     * @return the Blackjack point value of the card (0 if hidden)
     */
    public int getCost() {
        return hidden ? 0 : rank.getCost();
    }

    /**
     * Reveals the card (makes it unhidden).
     * Used when the dealer reveals their hole card.
     */
    public void reveal() {
        this.hidden = false;
    }

    /**
     * Checks if the card is currently hidden.
     *
     * @return true if the card is hidden, false if unhidden.
     */
    public boolean isHidden() {
        return this.hidden;
    }

    @Override
    public String toString() {
        if (hidden) {
            return "<закрытая карта>";
        }
        return rank + " " + suit + " (" + rank.getCost() + ")";
    }
}
