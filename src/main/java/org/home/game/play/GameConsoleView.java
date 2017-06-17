package org.home.game.play;

public class GameConsoleView implements GameView {
    @Override
    public void showWinnerNotification() {
        System.out.println("You won");
    }

    @Override
    public void showGameOverNotification() {
        System.out.println("Game Over");
    }
}
