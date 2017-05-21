package org.home.game.map.factory;

import org.home.game.map.Map;

import javax.annotation.Nonnull;

public interface MapFactory {
    @Nonnull
    Map create();
}
