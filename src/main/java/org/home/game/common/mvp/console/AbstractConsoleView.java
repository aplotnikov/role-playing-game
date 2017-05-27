package org.home.game.common.mvp.console;

import org.home.game.common.mvp.View;
import org.home.game.common.console.ui.Component;

import javax.annotation.Nonnull;

public abstract class AbstractConsoleView<T> implements View<T>, Component {

    protected T delegate;

    @Override
    public void setDelegate(@Nonnull T delegate) {
        this.delegate = delegate;
    }

    @Override
    public void erase() {
        Component.super.erase();
    }
}
