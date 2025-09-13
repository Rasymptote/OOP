package ru.nsu.babich;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    Player player = new Player();
    CardDeck deck = new CardDeck();

    @Test
    void checkAddWin() {
        assertEquals(0, player.getWins());

        player.addWin();
        assertEquals(1, player.getWins());

        player.addWin();
        player.addWin();
        assertEquals(3, player.getWins());
    }

    @Test
    void getWins() {
        player.addWin();
        assertEquals(1, player.getWins());
    }

    @Test
    void checkGetHand() {
        Hand hand = player.getHand();
        assertNotNull(hand);
        assertTrue(hand.cards.isEmpty());
        assertSame(hand, player.getHand());
    }

    @Test
    void checkResetHand() {
        player.getHand().addCard(deck);
        player.getHand().addCard(deck);
        assertEquals(2, player.getHand().cards.size());

        player.resetHand();
        assertNotNull(player.getHand());
        assertTrue(player.getHand().cards.isEmpty());
        assertEquals(0, player.getScore());

        player.addWin();
        player.resetHand();
        assertEquals(1, player.getWins());
    }

    @Test
    void checkGetScore() {
        assertEquals(0, player.getScore());

        player.getHand().cards.add(new Card(CardRank.TEN, CardSuit.HEARTS));
        player.getHand().cards.add(new Card(CardRank.SEVEN, CardSuit.SPADES));
        assertEquals(17, player.getScore());

        player.getHand().cards.add(new Card(CardRank.ACE, CardSuit.DIAMONDS));
        assertEquals(18, player.getScore());
    }
}