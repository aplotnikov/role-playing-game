package org.home.game.map.behaviour.user;

import org.home.game.common.console.ui.Menu;
import org.home.game.map.utils.MapPoint;

import javax.annotation.Nonnull;

public class UserMovementConsoleInput implements UserMovementInput {

    private final Menu<Movement> movementMenu = new Menu<>("Choose character movement:", Movement.values());

    @Nonnull
    @Override
    public MapPoint getNextPosition(@Nonnull MapPoint currentPosition) {
        movementMenu.draw();
        switch (movementMenu.chooseItem()) {
            case UP:
                return MapPoint.of(currentPosition.getLeft(), currentPosition.getTop() - 1);
            case DOWN:
                return MapPoint.of(currentPosition.getLeft(), currentPosition.getTop() + 1);
            case RIGHT:
                return MapPoint.of(currentPosition.getLeft() + 1, currentPosition.getTop());
            case LEFT:
                return MapPoint.of(currentPosition.getLeft() - 1, currentPosition.getTop());
            default:
                throw new IllegalStateException("Unsupported menu item");
        }
    }

    private enum Movement {
        UP("Move up"),
        DOWN("Move down"),
        RIGHT("Move right"),
        LEFT("Move left");

        private final String title;

        Movement(@Nonnull String title) {
            this.title = title;
        }

        @Override
        public String toString() {
            return title;
        }
    }
}
