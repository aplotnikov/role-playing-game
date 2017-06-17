package org.home.game.map.painter;

import org.home.game.map.GameMap;

import javax.annotation.Nonnull;

public interface MapPainter {
    void draw(@Nonnull GameMap map);

    void refresh();
}
