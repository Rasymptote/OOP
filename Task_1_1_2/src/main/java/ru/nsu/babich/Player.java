package ru.nsu.babich;

/**
 * Class represents a player in the Blackjack game.
 */
public class Player {
    private Hand hand;
    private int wins;

    /**
     * Constructs a new player with an empty hand and zero wins.
     */
    public Player() {
        this.hand = new Hand();
        this.wins = 0;
    }

    /**
     * Increments the player's win count by one.
     */
    public void addWin() {
        wins++;
    }

    /**
     * Returns the total number of wins by the player.
     *
     * @return wins
     */
    public int getWins() {
        return wins;
    }

    /**
     * Returns the player's current hand.
     *
     * @return hand
     */
    public Hand getHand() {
        return hand;
    }

    /**
     * Resets the player's hand to an empty state at the beginning of round.
     */
    public void resetHand() {
        this.hand = new Hand();
    }

    public int getScore() {
        return hand.calculateScore();
    }

}
