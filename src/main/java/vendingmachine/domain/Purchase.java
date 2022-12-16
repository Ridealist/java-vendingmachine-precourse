package vendingmachine.domain;

public class Purchase {
    private static final int MONEY_LOWER_BOUND = 100;
    private static final String INVALID_PRICE_MESSAGE = "투입 금액은 100원 이상이어야 합니다.";
    private static final String CANNOT_FIND_PRODUCT_MESSAGE = "해당 상품이 존재하지 않습니다.";
    private static final String CANNOT_BUY_PRODUCT_MESSAGE = "현재 남은 금액보다 비싼 상품은 구매할 수 없습니다.";

    private int money;

    public Purchase(int money) throws IllegalArgumentException {
        validateInputMoney(money);
        this.money = money;
    }

    private void validateInputMoney(int money) {
        if (money < MONEY_LOWER_BOUND) {
            throw new IllegalArgumentException(INVALID_PRICE_MESSAGE);
        }
    }

    public void buyProduct(String name) {
        Product product = ProductRepository.getFromName(name);
        if (product == null) {
            throw new IllegalArgumentException(CANNOT_FIND_PRODUCT_MESSAGE);
        }
        validateBuy(product, money);
        product.buy();
        money -= product.getPrice();
    }

    private void validateBuy(Product product, int money) {
        if (product.getPrice() > money) {
            throw new IllegalArgumentException(CANNOT_BUY_PRODUCT_MESSAGE);
        }
    }

    public int getMoney() {
        return money;
    }
}
