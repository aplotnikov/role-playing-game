package org.home.game.menu;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.home.game.common.mvp.AbstractConsoleView;
import org.home.game.common.ui.Menu;
import org.home.game.menu.MenuView.ActionDelegate;

import static lombok.AccessLevel.PRIVATE;

@FieldDefaults(level = PRIVATE, makeFinal = true)
public class MenuConsoleConsoleView extends AbstractConsoleView<ActionDelegate> implements MenuView {

    Menu<MainMenuItem> menu = new Menu<>("Main menu", MainMenuItem.values());

    @Override
    public void draw() {
        menu.draw();
        switch (menu.chooseItem()) {
            case START:
                delegate.onStartChosen();
                break;
            case RESUME:
                delegate.onResumeChosen();
            default:
        }
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
