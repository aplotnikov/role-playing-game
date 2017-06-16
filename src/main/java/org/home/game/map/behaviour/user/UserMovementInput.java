package org.home.game.map.behaviour.user;

import org.home.game.map.utils.Position;

import javax.annotation.Nonnull;

public interface UserMovementInput {
    @Nonnull
    Position getNextPosition(@Nonnull Position currentPosition);
}
