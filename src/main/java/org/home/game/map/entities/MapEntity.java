package org.home.game.map.entities;

import javax.annotation.Nonnull;
import java.util.Optional;
import java.util.function.Predicate;

import static java.util.Optional.empty;

public interface MapEntity {

    @Nonnull
    String getName();

    boolean isUser();

    default boolean canContainAnotherEntity() {
        return false;
    }

    default boolean containAnotherEntity() {
        return getInnerEntity().isPresent();
    }

    default boolean containUserCharacter() {
        return getInnerEntity()
                .map(entity -> entity.isUser() || entity.containUserCharacter())
                .orElse(false);
    }

    default boolean containTasks(@Nonnull Predicate<MapEntity> condition) {
        return condition.test(this) && !isUser()
                || getInnerEntity().map(entity -> entity.containTasks(condition)).orElse(false);
    }

    void take(@Nonnull MapEntity anotherEntity);

    void clear();

    default Optional<MapEntity> getInnerEntity() {
        return empty();
    }

    @Nonnull
    MapEntityType getType();

}
