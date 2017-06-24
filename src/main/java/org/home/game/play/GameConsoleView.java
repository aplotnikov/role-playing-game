package org.home.game.play;

import org.home.game.common.mvp.console.AbstractConsoleView;
import org.home.game.map.GameMap;
import org.home.game.map.entities.Entity;

import java.util.List;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.generate;

public class GameConsoleView extends AbstractConsoleView implements GameView {

    private static final char CELL_SEPARATOR = '|';

    private static final int MAP_SIZE_MARGIN = 2;

    @Override
    public void draw(GameMap map) {
        String lineSeparator = prepareLineSeparator(map.getEntities().size());

        System.out.println("MAP");
        System.out.println(lineSeparator);
        map.getEntities().forEach(this::drawLine);
        System.out.println(lineSeparator);
    }

    private String prepareLineSeparator(int numberEntitiesInLine) {
        return generate(() -> "-").limit(numberEntitiesInLine + MAP_SIZE_MARGIN).collect(joining());
    }

    private void drawLine(List<Entity> entities) {
        System.out.print(CELL_SEPARATOR);
        entities.forEach(this::drawEntity);
        System.out.println(CELL_SEPARATOR);
    }

    private void drawEntity(Entity entity) {
        switch (entity.getType()) {
            case ROAD:
                entity.getInnerEntity().ifPresent(this::drawEntity);
                if (!entity.containAnotherEntity()) {
                    System.out.print(' ');
                }
                break;
            case CHARACTER:
                if (entity.isUser()) {
                    System.out.print('U');
                    break;
                }
            default:
                System.out.print(entity.getName().charAt(0));
        }
    }

    @Override
    public void showWinnerNotification() {
        System.out.println("You won");
    }

    @Override
    public void showGameOverNotification() {
        System.out.println("Game Over");
    }

    @Override
    public void draw() {
        throw new UnsupportedOperationException("This method is not supported");
    }
}
