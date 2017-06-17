package org.home.game.play;

import org.home.game.map.GameMap;
import org.home.game.map.painter.MapPainter;

import javax.annotation.Nonnull;

public class GameImpl implements Game {

    private final GameMap map;

    private final MapPainter painter;

    public GameImpl(@Nonnull GameMap map, @Nonnull MapPainter painter) {
        this.map = map;
        this.painter = painter;
    }

    @Override
    public void start() {
        painter.draw(map);
        while (map.containsUserCharacter() && map.containsTasks()) {
            map.goToNextIteration();
            painter.refresh();
        }
    }
}
