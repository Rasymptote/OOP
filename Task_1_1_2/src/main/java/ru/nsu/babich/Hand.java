package ru.nsu.babich;

import java.util.ArrayList;

/**
 * Class represents a hand of cards.
 * Contains logic for calculating scores, adding cards, and managing card visibility.
 */
public class Hand {
    public ArrayList<Card> cards;

    /**
     * Constructs an empty hand.
     */
    public Hand() {
        this.cards = new ArrayList<>();
    }

    /**
     * Calculates the total value of cards in hand, handling aces.
     *
     * @return score
     */
    public int calculateScore() {
        int score = 0;
        int aceCount = 0;

        for (Card card : cards) {
            if (card.rank == CardRank.ACE) {
                aceCount++;
            }
            score += card.getCost();
        }

        while (score > 21 && aceCount > 0) {
            score -= 10;
            aceCount--;
        }

        return score;
    }

    /**
     * Adds unhidden card to the hand from the deck.
     *
     * @param deck the deck to draw the card from
     */
    public void addCard(CardDeck deck) {
        this.cards.add(deck.getCard());
    }

    /**
     * Returns the last card in the hand.
     *
     * @return the last Card object in the hand
     */
    public Card getLastCard() {
        if (cards.isEmpty()) {
            return null;
        }
        return this.cards.get(this.cards.size() - 1);
    }
    /**
     * Adds hidden card to the hand from the deck.
     *
     * @param deck the deck to draw the card from
     */
    public void getHiddenCard(CardDeck deck) {
        Card card = deck.getCard();
        Card hiddenCard = new Card(card.rank, card.suit, true);
        this.cards.add(hiddenCard);
    }

    /**
     * Reveals all hidden cards in the hand.
     */
    public void revealCards() {
        for (Card card : cards) {
            if (card.isHidden()) {
                card.reveal();
            }
        }
    }

    public boolean hasBlackjack() {
        return cards.size() == 2 && calculateScore() == 21;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(cards);

        if (!cards.get(1).isHidden()) {
            sb.append(" => ").append(calculateScore());
        }
        return sb.toString();
    }

}
