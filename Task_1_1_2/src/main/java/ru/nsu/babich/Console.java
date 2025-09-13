package ru.nsu.babich;

public class Console {

    public static void printWelcome() {
        System.out.println("Добро пожаловать в Блэкджек!");
    }

    public static void printStartRound(int number) {
        System.out.printf("Раунд %d\n", number);
    }

    public static void printHands(Hand userHand, Hand dealerHand) {
        System.out.printf("    Ваши карты: %s\n", userHand.toString());
        System.out.printf("    Карты дилера: %s\n\n", dealerHand.toString());
    }

    public static void printScore(int userWins, int dealerWins) {
        if (userWins > dealerWins) {
            System.out.printf("Счет %d:%d в вашу пользу.\n", userWins, dealerWins);
        } else if (dealerWins > userWins) {
            System.out.printf("Счет %d:%d в пользу дилера.\n", dealerWins, userWins);
        } else {
            System.out.printf("Счет %d:%d.\n", userWins, dealerWins);
        }
    }

    public static void printFinalScore(int userWins, int dealerWins) {
        System.out.printf("Игра завершена! Финальный счет:\nВы %d : %d Дилер\n", userWins, dealerWins);

    }
}
