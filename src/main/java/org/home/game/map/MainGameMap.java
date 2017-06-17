package org.home.game.map;

import org.apache.commons.lang.math.IntRange;
import org.home.game.map.behaviour.user.UserMovementInput;
import org.home.game.map.entities.Entity;
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

    private final List<List<Entity>> entities;

    private final Predicate<Entity> taskDetectionCondition;

    private final UserMovementInput userMovementInput;

    private final TaskCompletionStrategy taskCompletionStrategy;

    public MainGameMap(@Nonnull List<List<Entity>> entities,
                       @Nonnull UserMovementInput userMovementInput,
                       @Nonnull Predicate<Entity> taskDetectionCondition,
                       @Nonnull TaskCompletionStrategy taskCompletionStrategy) {
        this.entities = entities;
        this.taskDetectionCondition = taskDetectionCondition;
        this.userMovementInput = userMovementInput;
        this.taskCompletionStrategy = taskCompletionStrategy;
    }

    @Override
    public boolean containsUserCharacter() {
        return entities().anyMatch(Entity::containUserCharacter);
    }

    @Override
    public boolean containsTasks() {
        return entities().anyMatch(entity -> entity.containTasks(taskDetectionCondition));
    }

    @Nonnull
    private Stream<Entity> entities() {
        return entities.stream().flatMap(List::stream);
    }

    @Override
    public void goToNextIteration() {
        Position currentPosition = findFirstEntity(Entity::containUserCharacter)
                .orElseThrow(() -> new IllegalStateException("It is impossible to continue game when no user character on the map"));
        Position nextPosition = userMovementInput.getNextPosition(currentPosition);
        if (isValid(nextPosition, entities.size() - 1)) {
            moveUser(currentPosition, nextPosition);
        }
    }

    @Nonnull
    private Optional<Position> findFirstEntity(Predicate<Entity> condition) {
        return range(0, entities.size())
                .boxed()
                .flatMap(top -> zip(top, findEntityIndex(entities.get(top), condition)))
                .findFirst();
    }

    @Nonnull
    private IntStream findEntityIndex(@Nonnull List<Entity> entities, @Nonnull Predicate<Entity> condition) {
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
        Entity containerEntity = entityOn(currentPosition);
        Entity newContainerEntity = entityOn(nextPosition);

        if (!newContainerEntity.canContainAnotherEntity()) {
            return;
        }

        if (newContainerEntity.containTasks(taskDetectionCondition)) {
            Entity userCharacter = containerEntity.findEntity(Entity::isUser);
            Entity taskEntity = newContainerEntity.findEntity(taskDetectionCondition);

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
    private Entity entityOn(@Nonnull Position position) {
        return entities.get(position.getTop()).get(position.getLeft());
    }

    private boolean isUserAlive(@Nonnull Entity entity) {
        return entity.findEntity(Entity::isUser).isAlive();
    }

    @Nonnull
    @Override
    public List<List<Entity>> getEntities() {
        return entities;
    }
}
