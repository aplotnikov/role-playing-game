package org.home.game.menu;

import org.home.game.common.console.ui.Menu;
import org.home.game.common.mvp.console.AbstractConsoleView;
import org.home.game.menu.MainMenuView.ActionDelegate;

public class MainMainMenuConsoleConsoleView extends AbstractConsoleView<ActionDelegate> implements MainMenuView {

    private final Menu<MainMenuItem> menu = new Menu<>("Main menu", MainMenuItem.values());

    @Override
    public void draw() {
        menu.draw();
        switch (menu.chooseItem()) {
            case START:
                delegate.onStartChosen();
                break;
            case RESUME:
                delegate.onResumeChosen();
                break;
            default:
        }
    }

    enum MainMenuItem {
        START("Start new game"),
        RESUME("Resume previous game");

        private final String title;

        MainMenuItem(String title) {
            this.title = title;
        }

        @Override
        public String toString() {
            return title;
        }
    }
}
