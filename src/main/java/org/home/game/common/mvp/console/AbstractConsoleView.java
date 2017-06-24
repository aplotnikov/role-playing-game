package org.home.game.common.mvp.console;

import org.home.game.common.console.ui.Component;
import org.home.game.common.mvp.View;

public abstract class AbstractConsoleView<T> implements View<T>, Component {

    protected T delegate;

    @Override
    public void setDelegate(T delegate) {
        this.delegate = delegate;
    }

    @Override
    public void erase() {
        Component.super.erase();
    }
}
