package org.home.game.common.mvp;

public interface View<T> {
    void setDelegate(T delegate);

    void draw();

    void erase();
}
