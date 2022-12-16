package vendingmachine.view;

import java.util.Map;

public class OutputView {

    private static final String ERROR_PREFIX = "[ERROR] ";
    private static final String COIN_STATUS_FORMAT = "%d원 - %d개";
    private static final String MONEY_STATUS_FORMAT = "투입 금액: %d원";
    private static final String CHANGE_MESSAGE = "잔돈";



    public static void printError(String message) {
        System.out.println(ERROR_PREFIX + message);
        System.out.println();
    }

    public static void printCoinStatus(Map<Integer, Integer> coins) {
        System.out.println("자판기가 보유한 동전");
        for (int amount : coins.keySet()) {
            System.out.printf(COIN_STATUS_FORMAT, amount, coins.get(amount));
            System.out.println();
        }
        System.out.println();
    }

    public static void printInputMoneyStatus(int money) {
        System.out.printf(MONEY_STATUS_FORMAT, money);
        System.out.println();
    }

    public static void printChangeStatus(Map<Integer, Integer> coins) {
        System.out.println(CHANGE_MESSAGE);
        for (int amount : coins.keySet()) {
            if (coins.get(amount) == 0) {
                continue;
            }
            System.out.printf(COIN_STATUS_FORMAT, amount, coins.get(amount));
            System.out.println();
        }
    }
}
