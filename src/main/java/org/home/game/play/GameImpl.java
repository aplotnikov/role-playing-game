package org.home.game.play;

import org.home.game.map.GameMap;

public class GameImpl implements Game {

    private final GameMap map;

    private final GameView view;

    public GameImpl(GameMap map, GameView view) {
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
