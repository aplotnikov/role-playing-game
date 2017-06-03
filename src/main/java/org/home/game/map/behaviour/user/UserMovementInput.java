package org.home.game.map.behaviour.user;

import org.home.game.map.utils.MapPoint;

import javax.annotation.Nonnull;

public interface UserMovementInput {
    @Nonnull
    MapPoint getNextPosition(@Nonnull MapPoint currentPosition);
}
