package vendingmachine.domain;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ProductRepository {

    private static final List<Product> products = new ArrayList<>();

    public static void add(Product product) {
        products.add(product);
    }

    public static void clear() {
        products.clear();
    }

    public static Product getFromName(String name) {
        return products.stream()
                .filter(product -> product.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public static boolean canBuyAnyProduct() {
        return products.stream()
                .anyMatch(Product::canBuy);
    }

    public static boolean canAffordPrice(int money) {
        int prices = getMinPriceProduct().getPrice();
        return money >= prices;
    }

    private static Product getMinPriceProduct() {
        return products.stream()
                .min(Comparator.comparingInt(Product::getPrice))
                .orElseThrow(() -> new IllegalArgumentException());
    }
}
