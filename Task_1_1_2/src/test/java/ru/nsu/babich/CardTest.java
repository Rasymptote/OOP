package ru.nsu.babich;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    @Test
    void getAceCost() {
        var card = new Card(CardRank.ACE, CardSuit.HEARTS);
        assertEquals(11, card.getCost());
    }

    @Test
    void getHiddenCost() {
        var card = new Card(CardRank.JACK, CardSuit.SPADES, true);
        assertEquals(0, card.getCost());
    }

    @Test
    void checkString() {
        var card = new Card(CardRank.FIVE, CardSuit.SPADES);
        assertEquals("Пятерка Пики (5)", card.toString());
    }

    @Test
    void checkHiddenString() {
        var card = new Card(CardRank.TEN, CardSuit.HEARTS, true);
        assertEquals("<закрытая карта>", card.toString());
    }

    @Test
    void checkHiddenFlag() {
        var card = new Card(CardRank.EIGHT, CardSuit.DIAMONDS, true);
        assertTrue(card.isHidden());
    }

    @Test
    void checkRevealFlag() {
        var card = new Card(CardRank.TWO, CardSuit.HEARTS, true);
        card.reveal();
        assertFalse(card.isHidden());
    }

    @Test
    void checkSuit() {
        var card = new Card(CardRank.TEN, CardSuit.DIAMONDS);
        assertEquals(CardSuit.DIAMONDS, card.suit);
    }


}