package vendingmachine.domain;

/*
필드(인스턴스 변수)인 amount의 접근 제어자 private을 변경할 수 없다.
 */
public enum Coin {
    COIN_500(500),
    COIN_100(100),
    COIN_50(50),
    COIN_10(10);

    private final int amount;

    Coin(final int amount) {
        this.amount = amount;
    }

    // 추가 기능 구현
}
