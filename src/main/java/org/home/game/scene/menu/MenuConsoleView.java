package org.home.game.scene.menu;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.home.game.common.mvp.AbstractConsoleView;
import org.home.game.common.utils.console.ConsoleReader;
import org.home.game.scene.menu.MenuView.ActionDelegate;

import javax.annotation.Nonnull;

public class MenuConsoleView extends AbstractConsoleView<ActionDelegate> implements MenuView {

    public MenuConsoleView(@Nonnull ConsoleReader reader) {
        super(reader);
    }

    @Override
    public void draw() {
        printMainMenu(false);
        switch (readChosenMenuItem(MainMenuItem.values(), () -> printMainMenu(true))) {
            case START:
                delegate.onStartChosen();
                break;
            case RESUME:
                delegate.onResumeChosen();
            default:
        }
    }

    private void printMainMenu(boolean hasToPrintWarning) {
        printMenu("Main menu", hasToPrintWarning, MainMenuItem.values());
    }

    @RequiredArgsConstructor
    enum MainMenuItem {
        START("Start new game"),
        RESUME("Resume previous game");

        @NonNull
        private final String title;

        @Override
        public String toString() {
            return title;
        }
    }
}
