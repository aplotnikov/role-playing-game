package org.home.game.scene.menu;

import org.home.game.common.mvp.AbstractPresenter;

import javax.annotation.Nonnull;

public class MenuPresenter extends AbstractPresenter<MenuView> implements MenuView.ActionDelegate {

    public MenuPresenter(@Nonnull MenuView view) {
        super(view);
        this.view.setDelegate(this);
    }

    @Override
    public void onStartChosen() {

    }

    @Override
    public void onResumeChosen() {

    }
}
