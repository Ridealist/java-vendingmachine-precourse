package vendingmachine.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PurchaseTest {

    @BeforeAll
    static void init() {
        ProductRepository.clear();
        Product cola = new Product("콜라", 1500, 1);
        Product cider = new Product("사이다", 1000, 1);
        Product fanta = new Product("환타", 2000, 1);
        ProductRepository.add(cola);
        ProductRepository.add(cider);
        ProductRepository.add(fanta);
    }

    @DisplayName("유효하지 않은 투입 금액 테스트")
    @ParameterizedTest
    @ValueSource(ints = {-1, 50, 99})
    void newPurchase(int number) {
        assertThatThrownBy(() -> new Purchase(number))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("상품 가격보다 낮은 투입 금액 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"콜라", "사이다", "환타"})
    void buyCannotAffordProduct(String name) {
        Purchase purchase = new Purchase(500);
        assertThatThrownBy(() -> purchase.buyProduct(name))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("올바른 구입 테스트")
    @Test
    void buyProduct() {
        Purchase purchase = new Purchase(3000);
        purchase.buyProduct("콜라");
        assertThat(purchase.getMoney()).isEqualTo(1500);
        purchase.buyProduct("사이다");
        assertThat(purchase.getMoney()).isEqualTo(500);
    }
}