package org.home.game.play;

import org.home.game.map.GameMap;
import org.home.game.map.painter.MapPainter;

import javax.annotation.Nonnull;

public class GameImpl implements Game {

    private final GameMap map;

    private final MapPainter painter;

    private final GameView view;

    public GameImpl(@Nonnull GameMap map, @Nonnull MapPainter painter, @Nonnull GameView view) {
        this.map = map;
        this.painter = painter;
        this.view = view;
    }

    @Override
    public void start() {
        painter.draw(map);
        while (map.containsUserCharacter() && map.containsTasks()) {
            map.goToNextIteration();
            painter.refresh();
        }

        if (map.containsUserCharacter()) {
            view.showWinnerNotification();
        } else {
            view.showGameOverNotification();
        }
    }
}
