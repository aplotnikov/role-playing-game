package org.home.game.map.behaviour.user;

import org.home.game.common.console.ui.Menu;
import org.home.game.map.utils.Position;

import javax.annotation.Nonnull;

public class UserMovementConsoleInput implements UserMovementInput {

    private final Menu<Movement> menu = new Menu<>("Choose character movement:", Movement.values());

    @Nonnull
    @Override
    public Position getNextPosition(@Nonnull Position currentPosition) {
        menu.draw();
        switch (menu.chooseItem()) {
            case UP:
                return Position.of(currentPosition.getLeft(), currentPosition.getTop() - 1);
            case DOWN:
                return Position.of(currentPosition.getLeft(), currentPosition.getTop() + 1);
            case RIGHT:
                return Position.of(currentPosition.getLeft() + 1, currentPosition.getTop());
            case LEFT:
                return Position.of(currentPosition.getLeft() - 1, currentPosition.getTop());
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
