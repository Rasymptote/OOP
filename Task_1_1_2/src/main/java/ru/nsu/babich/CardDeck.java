package ru.nsu.babich;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Class represents a deck of cards.
 * Supports drawing cards and shuffling.
 */
public class CardDeck {
    private final ArrayList<Card> cards;

    /**
     * Constructs a new card deck and initializes it with 52 cards.
     */
    public CardDeck() {
        this.cards = new ArrayList<>();
        resetDeck();
    }

    private void resetDeck() {
        for (CardSuit suit : CardSuit.values()) {
            for (CardRank rank : CardRank.values()) {
                cards.add(new Card(rank, suit));
            }
        }
        shuffle();
    }

    /**
     * Draws one card from the top of the deck.
     * If the deck is empty, automatically resets and shuffles a new deck.
     *
     * @return the top card from the deck
     */
    public Card getCard() {
        if (cards.isEmpty()) {
            resetDeck();
        }
       return cards.remove(0);
    }

    /**
     * Shuffles the deck to randomize card order.
     */
    public void shuffle() {
        Collections.shuffle(cards);
    }

    public int getDeckSize() {
        return cards.size();
    }

}
