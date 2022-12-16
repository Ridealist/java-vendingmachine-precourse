package vendingmachine.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import vendingmachine.utils.InputParser;
import vendingmachine.utils.InputValidator;

public class InputView {
    private enum Message {
        INPUT_CHANGE_AMOUNT("자판기가 보유하고 있는 금액을 입력해 주세요."),
        INPUT_PRODUCT_LIST("상품명과 가격, 수량을 입력해 주세요."),
        INPUT_MONEY("투입 금액을 입력해 주세요."),
        INPUT_PRODUCT_NAME("구매할 상품명을 입력해 주세요.");

        private final String explanation;

        Message(String explanation) {
            this.explanation = explanation;
        }
    }

    public static int readChangeAmount() {
        System.out.println(Message.INPUT_CHANGE_AMOUNT.explanation);
        String input = readLineAndBreakOneLine();
        InputValidator.validateNumber(input);
        return Integer.parseInt(input);
    }
    
    public static List<List<String>> readProductList() throws IllegalArgumentException {
        System.out.println(Message.INPUT_PRODUCT_LIST.explanation);
        String input = readLineAndBreakOneLine();
        return InputParser.makeProductsList(input);
    }

    public static int readInputMoney() {
        System.out.println(Message.INPUT_MONEY.explanation);
        String input = readLineAndBreakOneLine();
        InputValidator.validateNumber(input);
        return Integer.parseInt(input);
    }

    public static String readProductName() {
        System.out.println(Message.INPUT_PRODUCT_NAME.explanation);
        String input = readLineAndBreakOneLine();
        return input.trim();
    }

    private static String readLineAndBreakOneLine() {
        String input = Console.readLine();
        System.out.println();
        return input;
    }
}
