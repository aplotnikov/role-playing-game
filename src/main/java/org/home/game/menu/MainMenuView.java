package org.home.game.menu;

import org.home.game.common.mvp.View;

public interface MainMenuView extends View<MainMenuView.ActionDelegate> {
    interface ActionDelegate {
        void onStartChosen();

        void onResumeChosen();
    }
}
