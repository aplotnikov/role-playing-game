package org.home.game.scene.menu;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.math.IntRange;
import org.apache.commons.lang.math.Range;
import org.home.game.common.mvp.AbstractConsoleView;
import org.home.game.common.utils.console.ConsoleIntegerReader;

import static org.home.game.scene.menu.MenuConsoleView.MainMenuItem.RESUME;
import static org.home.game.scene.menu.MenuConsoleView.MainMenuItem.START;

public class MenuConsoleView extends AbstractConsoleView<MenuView.ActionDelegate> implements MenuView {

    private static final Range AVAILABLE_MENU_ITEMS = new IntRange(1, 2);

    private static void printMainMenu(boolean hasToPrintWarning) {
        System.out.println("Main menu");
        System.out.println(START.toString());
        System.out.println(RESUME.toString());

        if (hasToPrintWarning) {
            System.out.println("Operation number is incorrect. Please, type correct one.");
        }

        System.out.println("Put operation's number which you want to do: ");
    }

    @Override
    public void draw() {
        printMainMenu(false);
        ConsoleIntegerReader reader = new ConsoleIntegerReader();
        int chosenMenuItem = reader.readUntil(
                line -> AVAILABLE_MENU_ITEMS.containsInteger(Integer.valueOf(line)),
                () -> {
                    erase();
                    printMainMenu(true);
                }
        );

        if (chosenMenuItem == START.index) {
            delegate.onStartChosen();
        } else if (chosenMenuItem == RESUME.index) {
            delegate.onResumeChosen();
        }
    }

    @RequiredArgsConstructor
    enum MainMenuItem {
        START(1, "Start new game"),
        RESUME(2, "Resume previous game");

        private final int index;
        private final String title;

        @Override
        public String toString() {
            return index + ". " + title;
        }
    }
}
