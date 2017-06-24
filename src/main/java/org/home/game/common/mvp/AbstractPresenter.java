package org.home.game.common.mvp;

public abstract class AbstractPresenter<T extends View> implements Presenter {

    protected final T view;

    public AbstractPresenter(T view) {
        this.view = view;
    }

    @Override
    public void show() {
        view.draw();
    }
}
