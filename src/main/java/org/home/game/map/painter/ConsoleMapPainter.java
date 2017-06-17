package org.home.game.map.painter;

import org.home.game.common.console.ui.Component;
import org.home.game.map.GameMap;
import org.home.game.map.entities.Entity;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.generate;

public class ConsoleMapPainter implements MapPainter, Component {

    private static final char CELL_SEPARATOR = '|';

    private static final int MAP_SIZE_MARGIN = 2;

    private String lineSeparator;

    @CheckForNull
    private GameMap map;

    @Override
    public void draw(@Nonnull GameMap map) {
        this.map = map;
        this.lineSeparator = prepareLineSeparator(map.getEntities().size());
        draw();
    }

    @Nonnull
    private String prepareLineSeparator(@Nonnegative int numberEntitiesInLine) {
        return generate(() -> "-").limit(numberEntitiesInLine + MAP_SIZE_MARGIN).collect(joining());
    }

    @Override
    public void refresh() {
        requireNonNull(map, "draw method with map has to be called before refresh method");
        draw(map);
    }

    @Override
    public void draw() {
        System.out.println("MAP");
        System.out.println(lineSeparator);
        map.getEntities().forEach(this::drawLine);
        System.out.println(lineSeparator);
    }

    private void drawLine(@Nonnull List<Entity> entities) {
        System.out.print(CELL_SEPARATOR);
        entities.forEach(this::drawEntity);
        System.out.println(CELL_SEPARATOR);
    }

    private void drawEntity(@Nonnull Entity entity) {
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
}
