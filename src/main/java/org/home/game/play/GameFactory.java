package org.home.game.play;

import javax.annotation.Nonnull;

public interface GameFactory {
    @Nonnull
    Game create();

    @Nonnull
    Game resume();
}
