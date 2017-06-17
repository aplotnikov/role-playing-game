package org.home.game.map.entities;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Optional;
import java.util.function.Predicate;

public interface MapEntity {

    @Nonnull
    String getName();

    @Nonnegative
    int getHealth();

    @Nonnegative
    int getAttackPower();

    boolean isUser();

    boolean isAlive();

    boolean canContainAnotherEntity();

    boolean containAnotherEntity();

    boolean containUserCharacter();

    boolean containTasks(@Nonnull Predicate<MapEntity> condition);

    MapEntity findEntity(@Nonnull Predicate<MapEntity> condition);

    void take(@Nonnull MapEntity anotherEntity);

    void isBeatenBy(@Nonnull MapEntity anotherEntity);

    void defense();

    boolean isDefended();

    void relax();

    void clear();

    @Nonnull
    Optional<MapEntity> getInnerEntity();

    @Nonnull
    MapEntityType getType();

}
