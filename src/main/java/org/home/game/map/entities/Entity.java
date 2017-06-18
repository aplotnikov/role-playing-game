package org.home.game.map.entities;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Optional;
import java.util.function.Predicate;

public interface Entity {

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

    boolean containTasks(@Nonnull Predicate<Entity> condition);

    Entity findEntity(@Nonnull Predicate<Entity> condition);

    void take(@Nonnull Entity anotherEntity);

    @Nonnegative
    int isBeatenBy(@Nonnull Entity anotherEntity);

    void defense();

    boolean isDefended();

    void relax();

    void clear();

    @Nonnull
    Optional<Entity> getInnerEntity();

    @Nonnull
    EntityType getType();

}
