package vendingmachine.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.function.Supplier;

public class InputView {
    private enum Message {
        INPUT_CHANGE_AMOUNT("자판기가 보유하고 있는 금액을 입력해 주세요."),
        INPUT_PRODUCT_LIST("상품명과 가격, 수량을 입력해 주세요."),
        INPUT_MONEY("투입 금액을 입력해 주세요."),
        INPUT_PRODUCT_NAME("구매할 상품명을 입력해 주세요."),

        EXCEPTION_NOT_A_NUMBER("숫자를 입력해 주세요.");

        private final String explanation;

        Message(String explanation) {
            this.explanation = explanation;
        }
    }

    public static int readChangeAmount() {
        System.out.println(Message.INPUT_CHANGE_AMOUNT.explanation);
        String input = readLineAndBreakOneLine();
        return validateNumber(input);
    }

    public static String readProductList() {
        return "";
    }

    public static int readInputMoney() {
        return 0;
    }

    public static String readProductName() {
        return "";
    }

    public static int validateNumber(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NullPointerException e) {
            throw new IllegalArgumentException(Message.EXCEPTION_NOT_A_NUMBER.explanation);
        }
    }

    private static String readLineAndBreakOneLine() {
        String input = Console.readLine();
        System.out.println();
        return input;
    }
}
