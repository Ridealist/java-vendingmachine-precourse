package vendingmachine.view;

import java.util.Map;

public class OutputView {

    private static final String ERROR_PREFIX = "[ERROR] ";
    private static final String STATUS_FORMAT = "%d원 - %d개";


    public static void printError() {

    }

    public static void printCoinStatus(Map<Integer, Integer> coins) {
        System.out.println("자판기가 보유한 동전");
        for (int amount : coins.keySet()) {
            System.out.printf(STATUS_FORMAT, amount, coins.get(amount));
            System.out.println();
        }
    }

    public static void printInputMoneyStatus() {

    }

    public static void printChangeStatus() {

    }
}
