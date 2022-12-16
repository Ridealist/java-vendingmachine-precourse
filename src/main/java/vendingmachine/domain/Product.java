package vendingmachine.domain;

public class Product {
    private static final int PRICE_LOWER_BOUND = 100;
    private static final int PRICE_UNIT = 10;
    private static final int AMOUNT_LOWER_BOUND = 1;

    private static final String INVALID_PRICE_MESSAGE = "상품 가격은 100원 이상이며, 10원으로 나누어 떨어져야 합니다.";
    private static final String INVALID_AMOUNT_MESSAGE = "상품 수량은 1개 이상이어야 합니다.";
    private static final String INVALID_NAME_MESSAGE = "상품 이름은 중복될 수 없습니다.";
    private static final String CANNOT_BUY_MESSAGE = "상품 수량이 다 떨어져 구매할 수 없습니다.";

    private final String name;
    private final int price;
    private int amount;

    public Product(String name, int price, int amount) throws IllegalArgumentException {
        validate(name, price, amount);
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    private void validate(String name, int price, int amount) {
        validateName(name);
        validatePrice(price);
        validateAmount(amount);
    }

    private void validatePrice(int price) {
        if (price < PRICE_LOWER_BOUND || price % PRICE_UNIT != 0) {
            throw new IllegalArgumentException(INVALID_PRICE_MESSAGE);
        }
    }

    private void validateAmount(int amount) {
        if (amount < AMOUNT_LOWER_BOUND) {
            throw new IllegalArgumentException(INVALID_AMOUNT_MESSAGE);
        }
    }

    private void validateName(String name) {
        if (ProductRepository.getFromName(name) != null) {
            throw new IllegalArgumentException(INVALID_NAME_MESSAGE);
        }
    }

    public void buy() {
        if (amount == 0) {
            throw new IllegalArgumentException(CANNOT_BUY_MESSAGE);
        }
        amount--;
    }

    public String getName() {
        return name;
    }

    public boolean canBuy() {
        return this.amount > 0;
    }

    public int getPrice() {
        return price;
    }
}
