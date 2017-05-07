package org.home.game.common.ui;

import lombok.experimental.FieldDefaults;
import org.apache.commons.lang.math.IntRange;
import org.apache.commons.lang.math.Range;
import org.home.game.common.ui.utils.ConsoleReader;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;
import static lombok.AccessLevel.PRIVATE;

@FieldDefaults(level = PRIVATE, makeFinal = true)
public class Menu<T extends Enum> implements Component {

    private static final int MENU_ITEM_OFFSET = 1;

    private static final Function<? super Enum, String> ENUM_TO_STRING =
            someEnum -> someEnum.ordinal() + MENU_ITEM_OFFSET + ". " + someEnum;

    ConsoleReader reader = new ConsoleReader();

    String title;

    T[] items;

    Runnable redrawWithWarningMessage = () -> {
        erase();
        draw();
        printMenuFooter(true);
    };

    Range acceptableItems;

    public Menu(@Nonnull String title, @Nonnull T... items) {
        if (items.length == 0) {
            throw new IllegalArgumentException("There are no configured menu items");
        }
        this.title = requireNonNull(title);
        this.items = items;
        this.acceptableItems = new IntRange(1, items.length);
    }

    @Override
    public void draw() {
        System.out.println(title);
        Stream.of(items).map(ENUM_TO_STRING).forEach(System.out::println);
    }

    public T chooseItem() {
        printMenuFooter(false);
        return items[readItemIndex()];
    }

    private void printMenuFooter(boolean hasToPrintWarning) {
        if (hasToPrintWarning) {
            System.out.println("Operation number is incorrect. Please, type correct one.");
        }
        System.out.println("Put operation's number which you want to do: ");
    }

    @Nonnegative
    private int readItemIndex() {
        return reader.readIntegerUntil(itemInRange(acceptableItems), redrawWithWarningMessage) - MENU_ITEM_OFFSET;
    }

    @Nonnull
    private Predicate<String> itemInRange(@Nonnull Range range) {
        return line -> range.containsInteger(Integer.parseInt(line));
    }
}
