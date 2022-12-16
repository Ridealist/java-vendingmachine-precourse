package vendingmachine.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ChangeTest {

    private final NumberGenerator randomNumberGenerator = new RandomNumberGenerator();

    @DisplayName("유효한 숫자 입력 에러 테스트")
    @ParameterizedTest
    @ValueSource(ints = {0, 10, 150, 100000})
    void getCoinsInValidNumber(int number) {
        Change change = new Change(number, randomNumberGenerator);
        assertThat(change.getCoins()).containsOnlyKeys(List.of(500, 100, 50, 10));
    }

    @DisplayName("유효하지 않은 숫자 입력 에러 테스트")
    @ParameterizedTest
    @ValueSource(ints = {-4, 1, 8})
    void invalidRangeNumber(int number) {
        assertThatThrownBy(() -> new Change(number, randomNumberGenerator))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("랜덤 숫자 생성된 이후 전체 합계가 올바른지 테스트")
    @ParameterizedTest
    @ValueSource(ints = {100, 1050, 1500, 100000})
    void getCoinsTotalCost(int number) {
        Change change = new Change(number, randomNumberGenerator);
        Map<Integer, Integer> coins = change.getCoins();
        int total = 0;
        for (Integer key : coins.keySet()) {
            total += coins.get(key) * key;
        }
        assertThat(total).isEqualTo(number);
    }
}