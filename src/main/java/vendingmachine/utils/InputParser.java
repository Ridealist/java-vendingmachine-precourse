package vendingmachine.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import vendingmachine.utils.InputValidator.Message;

public class InputParser {
    private static final String PRODUCT_UNIT_DELIMITER = ";";
    private static final String PRODUCT_DELIMITER = ",";
    private static final char LEFT_SQUARE_BRACKET = '[';
    private static final char RIGHT_SQUARE_BRACKET = ']';
    private static final int NAME_INDEX = 0;
    private static final int PRICE_INDEX = 1;
    private static final int AMOUNT_INDEX = 2;

    public static List<List<String>> makeProductsList(String input) throws IllegalArgumentException {
        List<List<String>> finalProducts = new ArrayList<>();
        List<String> products = splitProducts(input);
        for (String productUnit : products) {
            List<String> productDetails = splitProductDetail(productUnit.trim());
            validateInputs(productDetails);
            finalProducts.add(productDetails);
        }
        return finalProducts;
    }

    private static void validateInputs(List<String> productDetails) {
        InputValidator.validateNotEmpty(productDetails.get(NAME_INDEX));
        InputValidator.validateNumber(productDetails.get(PRICE_INDEX));
        InputValidator.validateNumber(productDetails.get(AMOUNT_INDEX));
    }

    private static List<String> splitProducts(String input) throws IllegalArgumentException {
        List<String> products = Arrays.asList(input.split(PRODUCT_UNIT_DELIMITER));
        InputValidator.validateNotEmpty(products);
        return Arrays.asList(input.split(PRODUCT_UNIT_DELIMITER));
    }

    private static List<String> splitProductDetail(String input) throws IllegalArgumentException {
        String contentInput = subtractContent(input);
        List<String> productDetail = Arrays.asList(contentInput.split(PRODUCT_DELIMITER));
        productDetail = productDetail.stream().map(String::trim).collect(Collectors.toList());
        InputValidator.validateLength(productDetail, 3);
        return productDetail;
    }

    private static String subtractContent(String inputUnit) {
        try {
            inputUnit = inputUnit.trim();
            int leftIndex = inputUnit.indexOf(LEFT_SQUARE_BRACKET);
            int rightIndex = inputUnit.indexOf(RIGHT_SQUARE_BRACKET);
            return inputUnit.substring(leftIndex + 1, rightIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException(Message.EXCEPTION_INVALID_INPUT.explanation);
        }
    }
}
