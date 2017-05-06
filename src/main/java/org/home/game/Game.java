package org.home.game;

import org.home.game.scene.menu.MenuConsoleView;
import org.home.game.scene.menu.MenuPresenter;

public class Game {
    public static void main(String[] args) {
        new MenuPresenter(new MenuConsoleView()).show();
    }
}
