package org.home.game.common.mvp;

import javax.annotation.Nonnull;

public interface View<T> {
    void setDelegate(@Nonnull T delegate);

    void draw();

    void erase();
}
