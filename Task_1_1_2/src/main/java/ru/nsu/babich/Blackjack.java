package ru.nsu.babich;

import java.util.Scanner;

/**
 * Blackjack class represents the Blackjack game.
 */
public class Blackjack {
    private final CardDeck deck;
    private final User user;
    private final Dealer dealer;
    private int roundNumber;
    private final Scanner scanner;

    /**
     * Constructs a new Blackjack game.
     * Initializes the deck, scanner, players and sets round number to 1.
     */
    public Blackjack() {
        this.deck = new CardDeck();
        this.scanner = new Scanner(System.in);
        this.user = new User(scanner);
        this.dealer = new Dealer();
        this.roundNumber = 1;
    }

    /**
     * Program entry point.
     *
     * @param args command-line arguments.
     */
    public static void main(String[] args) {
        Blackjack game = new Blackjack();
        game.startGame();
    }

    private void startGame() {
        Console.printWelcome();

        while (true) {
            playRound();

            System.out.println("Хотите сыграть еще раз? (1 - да, 0 - нет)");
            Integer choice = scanner.nextInt();
            if (!choice.equals(1)) {
                break;
            }
            roundNumber++;
        }

        Console.printFinalScore(user.getWins(), dealer.getWins());
        scanner.close();
    }

    private void initializeHands() {
        user.resetHand();
        dealer.resetHand();

        user.getHand().addCard(deck);
        user.getHand().addCard(deck);

        dealer.getHand().addCard(deck);
        dealer.getHand().getHiddenCard(deck);

        System.out.println("Дилер раздал карты");
    }

    private void playRound() {
        Console.printStartRound(roundNumber);
        initializeHands();
        Console.printHands(user.getHand(), dealer.getHand());

        if (user.getHand().hasBlackjack()) {
            System.out.println("Блэкджек! Вы выиграли раунд! ");
            user.addWin();
            Console.printScore(user.getWins(), dealer.getWins());
            return;
        }

        user.performTurn(dealer.getHand(), deck);

        if (user.getScore() > 21) {
            System.out.print("Вы проиграли из-за перебора! ");
            dealer.addWin();
            Console.printScore(user.getWins(), dealer.getWins());
            return;
        }

        dealer.performTurn(user.getHand(), deck);

        if (dealer.getScore() > 21) {
            System.out.print("Дилер проиграл из-за перебора! ");
            user.addWin();
            Console.printScore(user.getWins(), dealer.getWins());
            return;
        }

        int playerScore = user.getScore();
        int dealerScore = dealer.getScore();

        if (playerScore > dealerScore) {
            System.out.print("Вы выиграли раунд! ");
            user.addWin();
        } else if (dealerScore > playerScore) {
            System.out.print("Дилер выиграл раунд! ");
            dealer.addWin();
        } else {
            System.out.print("Ничья. ");
        }

        Console.printScore(user.getWins(), dealer.getWins());
    }
}
