package vendingmachine.domain;

public class Product {

    private final String name;
    private final int price;
    private final int amount;

    public static Product from() {

    }

    public void buy() {
        amount--;
    }
}
