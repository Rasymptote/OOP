package ru.nsu.babich;

import java.util.Scanner;

/**
 * Class representing the human user player in the Blackjack game.
 */
public class User extends Player {
    private final Scanner scanner;

    /**
     * Constructs a new user player.
     *
     * @param scanner for reading user input
     */
    public User(Scanner scanner) {
        super();
        this.scanner = scanner;
    }

    /**
     * Performs the user's turn according to Blackjack rules.
     *
     * @param dealerHand the dealer's hand for display purposes
     * @param deck the deck to draw cards from
     */
    public void performTurn(Hand dealerHand, CardDeck deck) {
        if (getScore() != 21) {
            System.out.println("Ваш ход");
            System.out.println("-------");
            System.out.println("Введите \"1\", чтобы взять карту, и \"0\", чтобы остановиться...");
            Integer answer = scanner.nextInt();

            while (!answer.equals(0)) {
                if (answer.equals(1)) {
                    getHand().addCard(deck);
                    System.out.printf("Вы открыли карту %s\n", getHand().getLastCard());
                    Console.printHands(getHand(), dealerHand);
                    if (getScore() > 21) {
                        break;
                    }
                }

                System.out.println("Введите \"1\", чтобы взять карту, и \"0\", чтобы остановиться...");
                answer = scanner.nextInt();
            }
        }
    }
}
