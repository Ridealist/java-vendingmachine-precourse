package vendingmachine.domain;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {

    private static final List<Product> products = new ArrayList<>();


    public static void save(Product product) {
        products.add(product);
    }

    public static Product getFromName(String name) {
        return products.stream()
                .filter(product -> product.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
}
