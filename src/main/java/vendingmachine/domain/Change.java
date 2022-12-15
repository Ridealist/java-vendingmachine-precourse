package vendingmachine.domain;

import java.util.Collections;
import java.util.Map;

public class Change {

    private final Map<Coin, Integer> coins;

    public Map<Coin, Integer> getCoins() {
        return Collections.unmodifiableMap(coins);
    }
}
