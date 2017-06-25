package org.home.game.common.console.ui;

import org.home.game.common.console.ui.utils.ConsoleReader;
import org.home.game.common.utils.IntRange;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Menu<T extends Enum> implements Component {

    private static final int MENU_ITEM_OFFSET = 1;

    private static final Function<? super Enum, String> ENUM_TO_STRING =
            someEnum -> someEnum.ordinal() + MENU_ITEM_OFFSET + ". " + someEnum;

    private final ConsoleReader reader = new ConsoleReader();

    private final String title;

    private final T[] items;

    private final Runnable redrawWithWarningMessage = () -> {
        redraw();
        printMenuFooter(true);
    };

    private final IntRange acceptableItems;

    @SafeVarargs
    public Menu(String title, T... items) {
        if (items.length == 0) {
            throw new IllegalArgumentException("There are no configured menu items");
        }
        this.title = title;
        this.items = items;

        this.acceptableItems = IntRange.of(1, items.length);
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
        System.out.println("Put operation's number which you want to do:");
    }

    private int readItemIndex() {
        return reader.readIntegerUntil(itemIsInAcceptableRange(), redrawWithWarningMessage) - MENU_ITEM_OFFSET;
    }

    private Predicate<String> itemIsInAcceptableRange() {
        return line -> acceptableItems.contains(Integer.parseInt(line));
    }
}
