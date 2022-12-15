package vendingmachine.domain;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class Change {
    private static final int TOTAL_AMOUNT_LOWER_BOUND = 0;
    private static final int AMOUNT_NUMBER_UNIT = 10;

    private static final String INVALID_NUMBER_RANGE_MESSAGE = "금액은 0보다 같거나 큰 10의 배수로 입력해야 합니다.";

    private final Map<Integer, Integer> coins;

    public Change(int number, NumberGenerator numberGenerator) {
        validateAmount(number);
        this.coins = initCoins();
        updateCoins(number, numberGenerator);
    }

    private Map<Integer, Integer> initCoins() {
        Map<Integer, Integer> coins = new LinkedHashMap<>();
        for (int coinAmount : Coin.getAmounts()) {
            coins.put(coinAmount, 0);
        }
        return coins;
    }

    private void updateCoins(int amount, NumberGenerator numberGenerator) {
        while (amount > 0) {
            int number = numberGenerator.generate();
            if (amount < number) {
                continue;
            }
            coins.put(number, coins.get(number) + 1);
            amount -= number;
        }
    }

    private void validateAmount(int number) {
        validateNumberRange(number);
        validateNumberUnit(number);
    }

    // TODO InputValidator에서 따로 관리 고민해보기!
    private void validateNumberRange(int number) {
        if (number < TOTAL_AMOUNT_LOWER_BOUND) {
            throw new IllegalArgumentException(INVALID_NUMBER_RANGE_MESSAGE);
        }
    }

    private void validateNumberUnit(int number) {
        if (number % AMOUNT_NUMBER_UNIT != 0) {
            throw new IllegalArgumentException(INVALID_NUMBER_RANGE_MESSAGE);
        }
    }

    public Map<Integer, Integer> getCoins() {
        return Collections.unmodifiableMap(coins);
    }
}
