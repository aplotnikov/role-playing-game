package org.home.game.common.utils.console;

import org.apache.commons.lang.StringUtils;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Scanner;
import java.util.function.Predicate;

import static java.util.Objects.nonNull;

public class ConsoleIntegerReader {

    private static final Predicate<String> NOT_BLANK = StringUtils::isNotBlank;

    private static final Predicate<String> NON_NEGATIVE = line -> Integer.parseInt(line) > 0;

    private static final Predicate<String> VALID_INPUT_DATA_CONDITION = NOT_BLANK.and(StringUtils::isNumeric).and(NON_NEGATIVE);

    @Nonnegative
    public int readUntil(@Nonnull Predicate<String> userCondition, @Nonnull Runnable onFail) {
        Predicate<String> retryCondition = VALID_INPUT_DATA_CONDITION.and(userCondition).negate();
        String line = null;
        do {
            if (nonNull(line)) {
                onFail.run();
            }
            line = new Scanner(System.in, "UTF-8").nextLine();
        } while (retryCondition.test(line));
        return Integer.parseInt(line);
    }
}
