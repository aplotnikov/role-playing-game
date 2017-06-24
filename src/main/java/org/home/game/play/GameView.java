package org.home.game.play;

import org.home.game.common.mvp.View;
import org.home.game.map.GameMap;

public interface GameView extends View {
    void draw(GameMap map);

    void showWinnerNotification();

    void showGameOverNotification();
}
