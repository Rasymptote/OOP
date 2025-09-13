package ru.nsu.babich;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardDeckTest {

    @Test
    void checkDeckInitialization() {
        var deck = new CardDeck();
        assertEquals(52, deck.getDeckSize());
    }

    @Test
    void checkGetCard() {
        var deck = new CardDeck();
        var deckSize = deck.getDeckSize();

        Card card = deck.getCard();
        assertEquals(deckSize - 1, deck.getDeckSize());

        assertNotNull(card);
        assertNotNull(card.rank);
        assertNotNull(card.suit);
        assertFalse(card.isHidden());
    }

    @Test
    void checkShuffle() {
        var deck = new CardDeck();
        var deck1 = new CardDeck();

        deck.shuffle();
        assertNotEquals(deck.getCard(), deck1.getCard());
    }
}