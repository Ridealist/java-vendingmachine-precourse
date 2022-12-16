package vendingmachine.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CoinTest {

    @DisplayName("정렬된 amount 리스트 반환하는지 확인")
    @Test
    void getAmountsInOrder() {
        assertThat(Coin.getAmounts()).isEqualTo(List.of(500, 100, 50, 10));
    }
}