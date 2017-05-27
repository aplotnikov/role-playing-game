package org.home.game.map;

import org.home.game.map.entities.MapEntity;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

class MainGameMap implements GameMap {

    private final List<List<MapEntity>> entities;

    MainGameMap(@Nonnull List<List<MapEntity>> entities) {
        this.entities = entities;
    }

    @Override
    public boolean containsUserCharacter() {
        return true;
    }

    @Override
    public boolean containsTasks() {
        return false;
    }

    @Override
    public void goToNextIteration() {

    }

    @Nonnull
    @Override
    public List<List<MapEntity>> getEntities() {
        return entities.stream()
                       .map(Collections::unmodifiableList)
                       .collect(collectingAndThen(toList(), Collections::unmodifiableList));
    }
}
