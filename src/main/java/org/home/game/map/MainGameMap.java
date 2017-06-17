package org.home.game.map;

import org.apache.commons.lang.math.IntRange;
import org.home.game.map.behaviour.user.UserMovementInput;
import org.home.game.map.entities.MapEntity;
import org.home.game.map.task.TaskCompletionStrategy;
import org.home.game.map.utils.Position;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.IntStream.range;

public class MainGameMap implements GameMap {

    private final List<List<MapEntity>> entities;

    private final Predicate<MapEntity> taskDetectionCondition;

    private final UserMovementInput userMovementInput;

    private final TaskCompletionStrategy taskCompletionStrategy;

    public MainGameMap(@Nonnull List<List<MapEntity>> entities,
                       @Nonnull UserMovementInput userMovementInput,
                       @Nonnull Predicate<MapEntity> taskDetectionCondition,
                       @Nonnull TaskCompletionStrategy taskCompletionStrategy) {
        this.entities = entities;
        this.taskDetectionCondition = taskDetectionCondition;
        this.userMovementInput = userMovementInput;
        this.taskCompletionStrategy = taskCompletionStrategy;
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
        Position currentPosition = findFirstEntity(MapEntity::containUserCharacter)
                .orElseThrow(() -> new IllegalStateException("It is impossible to continue game when no user character on the map"));
        Position nextPosition = userMovementInput.getNextPosition(currentPosition);
        if (isValid(nextPosition, entities.size() - 1)) {
            moveUser(currentPosition, nextPosition);
        }
    }

    @Nonnull
    private Optional<Position> findFirstEntity(Predicate<MapEntity> condition) {
        return range(0, entities.size())
                .boxed()
                .flatMap(top -> zip(top, findEntityIndex(entities.get(top), condition)))
                .findFirst();
    }

    @Nonnull
    private IntStream findEntityIndex(@Nonnull List<MapEntity> entities, @Nonnull Predicate<MapEntity> condition) {
        return range(0, entities.size()).filter(left -> condition.test(entities.get(left)));
    }

    @Nonnull
    private Stream<Position> zip(@Nonnegative int top, @Nonnull IntStream leftCoordinates) {
        return leftCoordinates.mapToObj(left -> Position.of(left, top));
    }

    private boolean isValid(@Nonnull Position position, @Nonnegative int maxCoordinate) {
        IntRange correctCoordinate = new IntRange(0, maxCoordinate);
        return correctCoordinate.containsInteger(position.getLeft())
                && correctCoordinate.containsInteger(position.getTop());
    }

    private void moveUser(@Nonnull Position currentPosition, @Nonnull Position nextPosition) {
        MapEntity containerEntity = entityOn(currentPosition);
        MapEntity newContainerEntity = entityOn(nextPosition);

        if (!newContainerEntity.canContainAnotherEntity()) {
            return;
        }

        if (newContainerEntity.containTasks(taskDetectionCondition)) {
            MapEntity userCharacter = containerEntity.findEntity(MapEntity::isUser);
            MapEntity taskEntity = newContainerEntity.findEntity(taskDetectionCondition);

            taskCompletionStrategy.complete(userCharacter, taskEntity);

            userCharacter.relax();
            taskEntity.relax();
        }

        containerEntity.getInnerEntity().ifPresent(userCharacter -> {
            containerEntity.clear();
            newContainerEntity.take(userCharacter);
        });

        if (!isUserAlive(newContainerEntity)) {
            newContainerEntity.clear();
        }
    }

    @Nonnull
    private MapEntity entityOn(@Nonnull Position position) {
        return entities.get(position.getTop()).get(position.getLeft());
    }

    private boolean isUserAlive(@Nonnull MapEntity entity) {
        return entity.findEntity(MapEntity::isUser).isAlive();
    }

    @Nonnull
    @Override
    public List<List<MapEntity>> getEntities() {
        return entities;
    }
}
