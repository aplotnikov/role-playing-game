package org.home.game.menu;

import lombok.experimental.FieldDefaults;
import org.home.game.common.mvp.AbstractPresenter;
import org.home.game.play.GameFactory;

import javax.annotation.Nonnull;

import static lombok.AccessLevel.PRIVATE;

@FieldDefaults(level = PRIVATE, makeFinal = true)
public class MenuPresenter extends AbstractPresenter<MenuView> implements MenuView.ActionDelegate {

    GameFactory gameFactory;

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
