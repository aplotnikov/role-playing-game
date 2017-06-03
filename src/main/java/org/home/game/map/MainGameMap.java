package org.home.game.map;

import org.home.game.map.behaviour.GameStrategy;
import org.home.game.map.entities.MapEntity;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class MainGameMap implements GameMap {

    private final List<List<MapEntity>> entities;

    private final GameStrategy userBehaviour;

    private final GameStrategy gameBehaviour;

    private final Predicate<MapEntity> taskDetectionCondition;

    public MainGameMap(
            @Nonnull List<List<MapEntity>> entities,
            @Nonnull GameStrategy userBehaviour,
            @Nonnull GameStrategy gameBehaviour,
            @Nonnull Predicate<MapEntity> taskDetectionCondition
    ) {
        this.entities = entities;
        this.userBehaviour = userBehaviour;
        this.gameBehaviour = gameBehaviour;
        this.taskDetectionCondition = taskDetectionCondition;
    }

    @Override
    public boolean containsUserCharacter() {
        return entities().anyMatch(MapEntity::containUserCharacter);
    }

    @Override
    public boolean containsTasks() {
        return entities().anyMatch(entity -> entity.containTasks(taskDetectionCondition));
    }

    @Nonnull
    private Stream<MapEntity> entities() {
        return entities.stream().flatMap(List::stream);
    }

    @Override
    public void goToNextIteration() {
        userBehaviour.process(entities);
        gameBehaviour.process(entities);
    }

    @Nonnull
    @Override
    public List<List<MapEntity>> getEntities() {
        return entities;
    }
}
