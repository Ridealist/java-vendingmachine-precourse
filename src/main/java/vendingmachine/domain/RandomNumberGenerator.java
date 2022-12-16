package vendingmachine.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;

public class RandomNumberGenerator implements NumberGenerator {

    @Override
    public int generate() {
        List<Integer> coins = Coin.getAmounts();
        return Randoms.pickNumberInList(coins);
    }
}
