package ru.nsu.babich;

/**
 * Class represents the dealer in the Blackjack game.
 */
public class Dealer extends Player {

    /**
     * Constructs a new dealer.
     */
    public Dealer() {
        super();
    }

    /**
     * Performs the dealer's turn according to Blackjack rules.
     *
     * @param playerHand the player's hand for display purposes
     * @param deck the deck to draw cards from
     */
    public void performTurn(Hand playerHand, CardDeck deck) {
        System.out.println("Ход дилера");
        System.out.println("-------");
        getHand().revealCards();
        System.out.printf("Дилер открывает закрытую карту %s\n", getHand().getLastCard());
        Console.printHands(playerHand, getHand());

        while (getScore() < 17) {
            getHand().addCard(deck);
            System.out.printf("Дилер открывает карту %s\n", getHand().getLastCard());
            Console.printHands(playerHand, getHand());
            if (getScore() > 21) {
                break;
            }
        }
    }
}
