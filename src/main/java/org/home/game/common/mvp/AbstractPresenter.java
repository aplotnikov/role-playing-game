package org.home.game.common.mvp;

import javax.annotation.Nonnull;

public abstract class AbstractPresenter<T extends View> implements Presenter {

    protected final T view;

    public AbstractPresenter(@Nonnull T view) {
        this.view = view;
    }

    @Override
    public void show() {
        view.draw();
    }
}
