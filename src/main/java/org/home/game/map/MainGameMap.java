package org.home.game.map;

import org.home.game.map.entities.MapEntity;
import org.home.game.map.entities.MapEntityType;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;
import static org.home.game.map.entities.MapEntityType.BEAR;
import static org.home.game.map.entities.MapEntityType.CHARACTER;
import static org.home.game.map.entities.MapEntityType.WOLF;

class MainGameMap implements GameMap {

    private static final Set<MapEntityType> ENEMIES = EnumSet.of(CHARACTER, WOLF, BEAR);

    private final List<List<MapEntity>> entities;

    MainGameMap(@Nonnull List<List<MapEntity>> entities) {
        this.entities = entities;
    }

    @Override
    public boolean containsUserCharacter() {
        return entities.stream().flatMap(List::stream).anyMatch(this::isUser);
    }

    private boolean isUser(@Nonnull MapEntity entity) {
        return entity.isUser() || entity.getInnerEntity().map(this::isUser).orElse(false);
    }

    @Override
    public boolean containsTasks() {
        return entities.stream().flatMap(List::stream).anyMatch(this::hasEnemy);
    }

    private boolean hasEnemy(@Nonnull MapEntity entity) {
        return ENEMIES.contains(entity.getType()) && !entity.isUser()
                || entity.getInnerEntity().map(this::hasEnemy).orElse(false);
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
