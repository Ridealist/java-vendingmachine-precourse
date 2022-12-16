package vendingmachine.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ProductRepositoryTest {

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

    @DisplayName("유효한 상품명 구매 가능 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"콜라", "사이다", "환타"})
    void getFromValidName(String name) {
        assertThat(ProductRepository.getFromName(name)).isInstanceOf(Product.class);
    }

    @DisplayName("유효하지 않은 상품명 구매 불가능 테스트")
    @Test
    void getFromInValidName() {
        assertThat(ProductRepository.getFromName("포도봉봉")).isEqualTo(null);
    }

    @DisplayName("모든 상품 소진 시 구매 불가능 테스트")
    @Test
    void canBuyAnyProduct() {
        assertThat(ProductRepository.canBuyAnyProduct()).isTrue();
        ProductRepository.getFromName("콜라").buy();
        ProductRepository.getFromName("사이다").buy();
        ProductRepository.getFromName("환타").buy();
        assertThat(ProductRepository.canBuyAnyProduct()).isFalse();
    }

    @DisplayName("남은 금액 상품 최소 금액 이상일 때 구매 가능 테스트")
    @Test
    void canAffordPrice() {
        assertThat(ProductRepository.canAffordPrice(1000)).isTrue();
    }

    @DisplayName("남은 금액 상품 최소 금액 이하 일때 구매 불가능 테스트")
    @Test
    void cannotAffordPrice() {
        assertThat(ProductRepository.canAffordPrice(999)).isFalse();
    }
}