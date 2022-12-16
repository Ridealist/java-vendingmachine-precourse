package vendingmachine.utils;

import java.util.List;

public class InputValidator {
    public enum Message {
        EXCEPTION_NOT_A_NUMBER("숫자를 입력해 주세요."),
        EXCEPTION_INVALID_SIZE("필요한 정보를 전부 입력해 주세요."),
        EXCEPTION_EMPTY_INPUT("빈칸은 허용되지 않습니다. 올바르게 입력해 주세요."),
        EXCEPTION_INVALID_INPUT("상품명, 가격, 수량은 쉼표로, 개별 상품은 대괄호([])로 묶어 세미콜론(;)으로 구분해서 입력해주세요.");

        public final String explanation;

        Message(String explanation) {
            this.explanation = explanation;
        }
    }

    public static void validateNumber(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(Message.EXCEPTION_NOT_A_NUMBER.explanation);
        }
    }

    public static void validateLength(List<String> inputs, int validLength) {
        if (inputs.size() != validLength) {
            throw new IllegalArgumentException(Message.EXCEPTION_INVALID_SIZE.explanation);
        }
    }

    public static void validateNotEmpty(List<String> inputs) {
        if (inputs.isEmpty()) {
            throw new IllegalArgumentException(Message.EXCEPTION_EMPTY_INPUT.explanation);
        }
        for (String input : inputs) {
            validateNotEmpty(input);
        }
    }

    public static void validateNotEmpty(String input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException(Message.EXCEPTION_EMPTY_INPUT.explanation);
        }
    }
}
