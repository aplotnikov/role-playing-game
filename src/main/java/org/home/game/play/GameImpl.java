package org.home.game.play;

import org.home.game.map.GameMap;

import javax.annotation.Nonnull;

public class GameImpl implements Game {

    private final GameMap map;

    private final GameView view;

    public GameImpl(@Nonnull GameMap map, @Nonnull GameView view) {
        this.map = map;
        this.view = view;
    }

    @Override
    public void start() {
        view.draw(map);
        while (map.containsUserCharacter() && map.containsTasks()) {
            map.goToNextIteration();
            view.draw(map);
        }

        if (map.containsUserCharacter()) {
            view.showWinnerNotification();
        } else {
            view.showGameOverNotification();
        }
    }
}
