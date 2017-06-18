package org.home.game.play;

import org.home.game.common.mvp.View;
import org.home.game.map.GameMap;

import javax.annotation.Nonnull;

public interface GameView extends View {

    void draw(@Nonnull GameMap map);

    void showWinnerNotification();

    void showGameOverNotification();
}
