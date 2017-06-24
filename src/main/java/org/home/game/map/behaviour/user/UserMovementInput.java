package org.home.game.map.behaviour.user;

import org.home.game.map.utils.Position;

public interface UserMovementInput {
    Position getNextPosition(Position currentPosition);
}
