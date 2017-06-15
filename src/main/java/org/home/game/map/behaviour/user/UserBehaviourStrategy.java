package org.home.game.map.behaviour.user;

import org.apache.commons.lang.math.IntRange;
import org.home.game.map.behaviour.GameStrategy;
import org.home.game.map.entities.MapEntity;
import org.home.game.map.utils.MapPoint;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.IntStream.range;

public class UserBehaviourStrategy implements GameStrategy {

    private final UserMovementInput userMovementInput;

    public UserBehaviourStrategy(@Nonnull UserMovementInput userMovementInput) {
        this.userMovementInput = userMovementInput;
    }

    @Override
    public void process(@Nonnull List<List<MapEntity>> entities) {
        MapPoint currentPosition = findUserCharacterPosition(entities);
        MapPoint nextPosition = userMovementInput.getNextPosition(currentPosition);
        if (isValid(nextPosition, entities.size() - 1)) {
            moveUser(currentPosition, nextPosition, entities);
        }
    }

    private boolean isValid(@Nonnull MapPoint position, @Nonnegative int maxCoordinate) {
        IntRange correctCoordinate = new IntRange(0, maxCoordinate);
        return correctCoordinate.containsInteger(position.getLeft())
                && correctCoordinate.containsInteger(position.getTop());
    }

    private void moveUser(@Nonnull MapPoint currentPosition, @Nonnull MapPoint nextPosition, @Nonnull List<List<MapEntity>> entities) {
        MapEntity containerEntity = entityOn(currentPosition, entities);
        MapEntity newContainerEntity = entityOn(nextPosition, entities);

        if (!newContainerEntity.canContainAnotherEntity() || newContainerEntity.containAnotherEntity()) {
            return;
        }

        containerEntity.getInnerEntity().ifPresent(userCharacter -> {
            containerEntity.clear();
            newContainerEntity.take(userCharacter);
        });
    }

    @Nonnull
    private MapEntity entityOn(@Nonnull MapPoint position, @Nonnull List<List<MapEntity>> entities) {
        return entities.get(position.getTop()).get(position.getLeft());
    }

    @Nonnull
    private MapPoint findUserCharacterPosition(@Nonnull List<List<MapEntity>> entities) {
        return range(0, entities.size())
                .boxed()
                .flatMap(top -> zip(top, findUserCharacterIndex(entities.get(top))))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("It is impossible to continue game when no user character on the map"));
    }

    @Nonnull
    private IntStream findUserCharacterIndex(@Nonnull List<MapEntity> entities) {
        return range(0, entities.size()).filter(left -> entities.get(left).containUserCharacter());
    }

    @Nonnull
    private Stream<MapPoint> zip(@Nonnegative int top, @Nonnull IntStream leftCoordinates) {
        return leftCoordinates.mapToObj(left -> MapPoint.of(left, top));
    }
}
