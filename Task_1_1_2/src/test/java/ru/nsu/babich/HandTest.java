package ru.nsu.babich;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class HandTest {

    Hand hand = new Hand();
    CardDeck deck = new CardDeck();

    @Test
    void checkCalculateScore() {
        hand.cards.add(new Card(CardRank.FIVE, CardSuit.HEARTS));
        hand.cards.add(new Card(CardRank.SEVEN, CardSuit.SPADES));

        assertEquals(12, hand.calculateScore());
    }

    @Test
    void checkCalculateScoreWithHiddenCards() {
        hand.cards.add(new Card(CardRank.TEN, CardSuit.CLUBS));
        hand.cards.add(new Card(CardRank.KING, CardSuit.SPADES, true));

        assertEquals(10, hand.calculateScore());
    }

    @Test
    void checkCalculateScoreWithAces() {
        hand.cards.add(new Card(CardRank.ACE, CardSuit.HEARTS));
        hand.cards.add(new Card(CardRank.ACE, CardSuit.SPADES));
        hand.cards.add(new Card(CardRank.EIGHT, CardSuit.DIAMONDS));

        assertEquals(20, hand.calculateScore());
    }

    @Test
    void checkAddCard() {
        hand.addCard(deck);

        assertEquals(1, hand.cards.size());

        Card card = hand.cards.get(0);

        assertNotNull(card);
        assertFalse(card.isHidden());
    }

    @Test
    void checkGetHiddenCard() {
        hand.getHiddenCard(deck);

        assertEquals(1, hand.cards.size());

        Card card = hand.cards.get(0);

        assertNotNull(card);
        assertTrue(card.isHidden());
        assertEquals(0, card.getCost());
    }

    @Test
    void checkRevealCards() {
        hand.addCard(deck);
        hand.getHiddenCard(deck);

        Card hiddenCard = hand.cards.get(1);

        assertTrue(hiddenCard.isHidden());

        hand.revealCards();

        assertFalse(hiddenCard.isHidden());
        assertTrue(hiddenCard.getCost() > 0);
    }

    @Test
    void checkHasBlackjack() {
        hand.cards.add(new Card(CardRank.ACE, CardSuit.HEARTS));
        hand.cards.add(new Card(CardRank.KING, CardSuit.SPADES));

        assertTrue(hand.hasBlackjack());
    }

    @Test
    void checkToString() {
        hand.cards.add(new Card(CardRank.FIVE, CardSuit.HEARTS));
        hand.cards.add(new Card(CardRank.SEVEN, CardSuit.CLUBS));

        String result = hand.toString();

        assertTrue(result.contains("Пятерка Червы"));
        assertTrue(result.contains("Семерка Трефы"));
        assertTrue(result.contains("=> 12"));
    }

    @Test
    void checkToStringWithHiddenCard() {
        hand.cards.add(new Card(CardRank.TEN, CardSuit.HEARTS));
        hand.cards.add(new Card(CardRank.KING, CardSuit.SPADES, true));

        String result = hand.toString();

        assertTrue(result.contains("Десятка Червы"));
        assertTrue(result.contains("<закрытая карта>"));
        assertFalse(result.contains("=>"));
    }

    @Test
    void checkGetLastCard() {
        hand.addCard(deck);
        Card firstCard = hand.getLastCard();

        hand.addCard(deck);
        Card secondCard = hand.getLastCard();

        assertNotEquals(firstCard, secondCard);
        assertEquals(hand.cards.get(1), secondCard);

        hand.addCard(deck);
        Card thirdCard = hand.getLastCard();

        assertEquals(hand.cards.get(2), thirdCard);
        assertNotEquals(secondCard, thirdCard);
    }
}