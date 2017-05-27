package org.home.game.map.factory;

import org.home.game.map.GameMap;

import javax.annotation.Nonnull;

public interface MapFactory {
    @Nonnull
    GameMap create();
}
