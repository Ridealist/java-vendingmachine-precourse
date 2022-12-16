package vendingmachine.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ProductTest {

    @AfterEach
    void clearData() {
        ProductRepository.clear();
    }

    @DisplayName("중복된 상품명 입력 에러 테스트")
    @ParameterizedTest
    @CsvSource(value = {"콜라, 1500, 20"})
    void DuplicateName(String name, int price, int amount) {
        ProductRepository.add(new Product(name, price, amount));
        assertThatThrownBy(() -> new Product(name, price, amount))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("유효하지 않은 수량 입력 에러 테스트")
    @ParameterizedTest
    @CsvSource(value = {"콜라, 1500, -4", "콜라, 1500, 0"})
    void invalidRangeAmount(String name, int price, int amount) {
        assertThatThrownBy(() -> new Product(name, price, amount))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("유효하지 않은 가격 입력 에러 테스트")
    @ParameterizedTest
    @CsvSource(value = {"콜라, 0, 5", "콜라, 101, 5", "콜라, -5, 5"})
    void invalidRangePrice(String name, int price, int amount) {
        assertThatThrownBy(() -> new Product(name, price, amount))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("수량 0일 때 주문 에러 테스트")
    @Test
    void buyInAmountZero() {
        Product product = new Product("콜라", 1500, 1);
        product.buy();
        assertThatThrownBy(() -> product.buy())
                .isInstanceOf(IllegalArgumentException.class);
    }
}