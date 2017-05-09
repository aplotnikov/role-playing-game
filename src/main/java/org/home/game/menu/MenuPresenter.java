package org.home.game.menu;

import lombok.experimental.FieldDefaults;
import org.home.game.GamePlay;
import org.home.game.common.mvp.AbstractPresenter;
import org.home.game.map.MapFactory;

import javax.annotation.Nonnull;

import static lombok.AccessLevel.PRIVATE;

@FieldDefaults(level = PRIVATE, makeFinal = true)
public class MenuPresenter extends AbstractPresenter<MenuView> implements MenuView.ActionDelegate {

    MapFactory mapFactory;

    public MenuPresenter(@Nonnull MenuView view, @Nonnull MapFactory mapFactory) {
        super(view);
        this.view.setDelegate(this);
        this.mapFactory = mapFactory;
    }

    @Override
    public void onStartChosen() {
        new GamePlay(mapFactory.create()).start();
    }

    @Override
    public void onResumeChosen() {

    }
}
