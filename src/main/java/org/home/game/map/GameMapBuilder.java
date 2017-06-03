package org.home.game.map;

import org.home.game.map.entities.MapEntity;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;

public class GameMapBuilder {

    private final List<List<MapEntity>> entities = new ArrayList<>();

    private long size = 0;

    private GameMapBuilder() {}

    @Nonnull
    public static GameMapBuilder map() {
        return new GameMapBuilder();
    }

    @Nonnull
    public GameMapBuilder line(@Nonnull MapEntity... entities) {
        if (entities.length == 0) {
            throw new IllegalStateException("It is impossible to create empty line of map");
        }

        if (size == 0) {
            size = entities.length;
        } else if (size != entities.length) {
            throw new IllegalStateException("It is impossible to create map lines with different size");
        }

        this.entities.add(unmodifiableList(asList(entities)));
        return this;
    }

    @Nonnull
    public List<List<MapEntity>> create() {
        if (entities.isEmpty()) {
            throw new IllegalStateException("It is impossible to create empty map");
        }

        return unmodifiableList(entities);
    }
}
