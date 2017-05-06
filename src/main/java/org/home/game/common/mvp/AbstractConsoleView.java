package org.home.game.common.mvp;

import javax.annotation.Nonnull;

import static java.util.stream.IntStream.rangeClosed;

public abstract class AbstractConsoleView<T> implements View<T> {

    protected T delegate;

    @Override
    public void setDelegate(@Nonnull T delegate) {
        this.delegate = delegate;
    }

    @Override
    public void erase() {
        rangeClosed(1, 50).forEach(value -> System.out.println());
    }
}
