package org.home.game.menu;

import org.home.game.common.mvp.AbstractPresenter;
import org.home.game.play.GameFactory;

import javax.annotation.Nonnull;

public class MenuPresenter extends AbstractPresenter<MenuView> implements MenuView.ActionDelegate {

    private final GameFactory gameFactory;

    public MenuPresenter(@Nonnull MenuView view, @Nonnull GameFactory gameFactory) {
        super(view);
        this.view.setDelegate(this);
        this.gameFactory = gameFactory;
    }

    @Override
    public void onStartChosen() {
        gameFactory.create().start();
    }

    @Override
    public void onResumeChosen() {
        gameFactory.resume().start();
    }
}
