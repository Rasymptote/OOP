package ru.nsu.babich;

/**
 * Class provides static methods for displaying game information.
 */
public class Console {

    /**
     * Prints a welcome message to the player at the start of the game.
     */
    public static void printWelcome() {
        System.out.println("Добро пожаловать в Блэкджек!");
    }

    /**
     * Prints the start of a new round with the round number.
     *
     * @param number the round number to display
     */
    public static void printStartRound(int number) {
        System.out.printf("Раунд %d\n", number);
    }

    /**
     * Prints both the player's and dealer's hands.
     *
     * @param userHand the player's hand to display
     * @param dealerHand the dealer's hand to display
     */
    public static void printHands(Hand userHand, Hand dealerHand) {
        System.out.printf("    Ваши карты: %s\n", userHand.toString());
        System.out.printf("    Карты дилера: %s\n\n", dealerHand.toString());
    }

    /**
     * Prints the current score between player and dealer.
     *
     * @param userWins the number of rounds won by the player
     * @param dealerWins the number of rounds won by the dealer
     */
    public static void printScore(int userWins, int dealerWins) {
        if (userWins > dealerWins) {
            System.out.printf("Счет %d:%d в вашу пользу.\n", userWins, dealerWins);
        } else if (dealerWins > userWins) {
            System.out.printf("Счет %d:%d в пользу дилера.\n", dealerWins, userWins);
        } else {
            System.out.printf("Счет %d:%d.\n", userWins, dealerWins);
        }
    }

    /**
     * Prints the final score at the end of the game.
     *
     * @param userWins the total number of rounds won by the player
     * @param dealerWins the total number of rounds won by the dealer
     */
    public static void printFinalScore(int userWins, int dealerWins) {
        System.out.println("Игра завершена!");
        System.out.printf("Финальный счет:\nВы %d : %d Дилер\n", userWins, dealerWins);

    }
}
