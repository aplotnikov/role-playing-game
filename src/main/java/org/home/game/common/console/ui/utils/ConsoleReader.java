package org.home.game.common.console.ui.utils;

import java.util.Scanner;
import java.util.function.Predicate;

import static java.util.Objects.nonNull;

public class ConsoleReader {

    private static final Predicate<String> NOT_BLANK =
            text -> nonNull(text) && !text.isEmpty() && text.chars().noneMatch(Character::isWhitespace);

    private static final Predicate<String> IS_NUMERIC =
            text -> nonNull(text) && text.chars().allMatch(Character::isDigit);

    private static final Predicate<String> NON_NEGATIVE = line -> Integer.parseInt(line) > 0;

    private static final Predicate<String> VALID_INPUT_DATA_CONDITION = NOT_BLANK.and(IS_NUMERIC).and(NON_NEGATIVE);

    public int readIntegerUntil(Predicate<String> userCondition, Runnable onFail) {
        Predicate<String> retryCondition = VALID_INPUT_DATA_CONDITION.and(userCondition).negate();
        String line = null;
        do {
            if (nonNull(line)) {
                onFail.run();
            }
            line = readString();
        } while (retryCondition.test(line));
        return Integer.parseInt(line);
    }

    private Scanner scanner() {
        return new Scanner(System.in, "UTF-8");
    }

    public String readString() {
        return scanner().nextLine();
    }
}
