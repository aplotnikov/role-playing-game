package org.home.game.map.factory;

import org.home.game.map.GameMap;

public interface MapFactory {
    GameMap create();

    GameMap restore();
}
