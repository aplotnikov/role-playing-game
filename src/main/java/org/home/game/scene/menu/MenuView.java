package org.home.game.scene.menu;

import org.home.game.common.mvp.View;

public interface MenuView extends View<MenuView.ActionDelegate> {

    interface ActionDelegate {
        void onStartChosen();

        void onResumeChosen();
    }
}
