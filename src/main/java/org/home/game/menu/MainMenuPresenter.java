package org.home.game.menu;

import org.home.game.common.mvp.AbstractPresenter;
import org.home.game.play.GameFactory;

public class MainMenuPresenter extends AbstractPresenter<MainMenuView> implements MainMenuView.ActionDelegate {

    private final GameFactory gameFactory;

    public MainMenuPresenter(MainMenuView view, GameFactory gameFactory) {
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
