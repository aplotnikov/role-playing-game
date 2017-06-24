package org.home.game.map.entities;

import java.io.Serializable;
import java.util.Optional;
import java.util.function.Predicate;

public interface Entity extends Serializable {

    String getName();

    int getHealth();

    int getAttackPower();

    boolean isUser();

    boolean isAlive();

    boolean canContainAnotherEntity();

    boolean containAnotherEntity();

    boolean containUserCharacter();

    boolean containTasks(Predicate<Entity> condition);

    Entity findEntity(Predicate<Entity> condition);

    void take(Entity anotherEntity);

    int isBeatenBy(Entity anotherEntity);

    void defense();

    boolean isDefended();

    void relax();

    void clear();

    Optional<Entity> getInnerEntity();

    EntityType getType();
}
