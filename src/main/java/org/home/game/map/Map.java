package org.home.game.map;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.home.game.common.ui.Component;
import org.home.game.map.objects.MapObject;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.generate;
import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor(access = PRIVATE)
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class Map implements Component {

    private static final char CELL_SEPARATOR = '|';

    private static final int MAP_SIZE_MARGIN = 2;

    List<List<MapObject>> objects;

    String lineSeparator;

    private Map(@Nonnull List<List<MapObject>> objects) {
        this.objects = objects;
        this.lineSeparator = prepareLineSeparator();
    }

    @Nonnull
    public static Builder map() {
        return new Builder();
    }

    @Nonnull
    private String prepareLineSeparator() {
        return generate(() -> "-").limit(objects.get(0).size() + MAP_SIZE_MARGIN).collect(joining());
    }

    public boolean containsUserCharacter() {
        return true;
    }

    public boolean containsTasks() {
        return false;
    }

    public void goToNextIteration() {

    }

    @Override
    public void draw() {
        System.out.println("MAP");
        System.out.println(lineSeparator);
        objects.forEach(this::drawLine);
        System.out.println(lineSeparator);
    }

    private void drawLine(@Nonnull List<MapObject> objects) {
        System.out.print(CELL_SEPARATOR);
        objects.forEach(MapObject::draw);
        System.out.println(CELL_SEPARATOR);
    }

    public static class Builder {

        final List<List<MapObject>> objects = new ArrayList<>();

        long size = 0;

        @Nonnull
        public Builder line(@Nonnull MapObject... objects) {
            if (objects.length == 0) {
                throw new IllegalStateException("It is impossible to create empty line of map");
            }

            if (size == 0) {
                size = objects.length;
            } else if (size != objects.length) {
                throw new IllegalStateException("It is impossible to create map lines with different size");
            }

            this.objects.add(asList(objects));
            return this;
        }

        @Nonnull
        public Map create() {
            if (objects.isEmpty()) {
                throw new IllegalStateException("It is impossible to create empty map");
            }

            return new Map(objects);
        }
    }
}
