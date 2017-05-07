package org.home.game.common.mvp;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.math.IntRange;
import org.apache.commons.lang.math.Range;
import org.home.game.common.utils.console.ConsoleReader;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static java.util.stream.IntStream.rangeClosed;

@RequiredArgsConstructor
public abstract class AbstractConsoleView<T> implements View<T> {

    private static final int MENU_ITEM_OFFSET = 1;

    private static final Function<Enum, String> ENUM_TO_STRING =
            someEnum -> someEnum.ordinal() + MENU_ITEM_OFFSET + ". " + someEnum;

    @NonNull
    protected final ConsoleReader reader;

    protected T delegate;

    @Override
    public void setDelegate(@Nonnull T delegate) {
        this.delegate = delegate;
    }

    @Override
    public void erase() {
        rangeClosed(1, 50).forEach(value -> System.out.println());
    }

    protected void printMenu(@Nonnull String title, boolean hasToPrintWarning, Enum... items) {
        if (items.length == 0) {
            throw new IllegalStateException("There are no menu items");
        }
        System.out.println(title);
        Stream.of(items).map(ENUM_TO_STRING).forEach(System.out::println);
        printMenuFooter(hasToPrintWarning);
    }

    private void printMenuFooter(boolean hasToPrintWarning) {
        if (hasToPrintWarning) {
            System.out.println("Operation number is incorrect. Please, type correct one.");
        }
        System.out.println("Put operation's number which you want to do: ");
    }

    protected <E> E readChosenMenuItem(@Nonnull E[] items, @Nonnull Runnable onFail) {
        return items[readItemIndex(new IntRange(1, items.length), onFail)];
    }

    @Nonnegative
    private int readItemIndex(@Nonnull Range acceptableItems, @Nonnull Runnable onFail) {
        return reader.readIntegerUntil(
                itemInRange(acceptableItems),
                () -> {
                    erase();
                    onFail.run();
                }
        ) - MENU_ITEM_OFFSET;
    }

    @Nonnull
    private Predicate<String> itemInRange(@Nonnull Range range) {
        return line -> range.containsInteger(Integer.parseInt(line));
    }
}
