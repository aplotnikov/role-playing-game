package org.home.game.map.entities;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

public class SimpleEntity implements Entity {

    private final String name;

    private final EntityType type;

    private final int attackPower;

    private int health;

    private boolean defended;

    public SimpleEntity(@Nonnull String name, @Nonnull EntityType type, @Nonnegative int attackPower) {
        this.name = name;
        this.type = type;
        this.attackPower = attackPower;
        this.health = 100;
        this.defended = false;
    }

    @Nonnull
    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public int getAttackPower() {
        return attackPower;
    }

    @Nonnull
    @Override
    public EntityType getType() {
        return type;
    }

    @Override
    public boolean isUser() {
        return false;
    }

    @Override
    public boolean isAlive() {
        return health > 0;
    }

    @Override
    public boolean canContainAnotherEntity() {
        return false;
    }

    @Override
    public boolean containAnotherEntity() {
        return getInnerEntity().isPresent();
    }

    @Override
    public boolean containUserCharacter() {
        return getInnerEntity()
                .filter(entity -> entity.isUser() || entity.containUserCharacter())
                .isPresent();
    }

    @Override
    public boolean containTasks(@Nonnull Predicate<Entity> condition) {
        return condition.test(this) && !isUser()
                || getInnerEntity().filter(entity -> entity.containTasks(condition)).isPresent();
    }

    @Override
    public Entity findEntity(@Nonnull Predicate<Entity> condition) {
        return getInnerEntity()
                .map(entity -> condition.test(entity) ? entity : entity.findEntity(condition))
                .orElseThrow(() -> new IllegalStateException("There is no entities with such condition"));
    }

    @Nonnull
    @Override
    public Optional<Entity> getInnerEntity() {
        return Optional.empty();
    }

    @Override
    public void take(@Nonnull Entity anotherEntity) {
        throw new UnsupportedOperationException("This method is not supported.");
    }

    @Override
    public void isBeatenBy(@Nonnull Entity anotherEntity) {
        if (defended) {
            defended = false;
        } else if (health >= anotherEntity.getAttackPower()) {
            health -= anotherEntity.getAttackPower();
        } else {
            health = 0;
        }
    }

    @Override
    public void defense() {
        defended = true;
    }

    @Override
    public boolean isDefended() {
        return defended;
    }

    @Override
    public void relax() {
        defended = false;
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("This method is not supported.");
    }

    @Override
    public boolean equals(Object anotherObject) {
        if (this == anotherObject) {
            return true;
        }

        if (anotherObject == null || getClass() != anotherObject.getClass()) {
            return false;
        }

        SimpleEntity anotherEntity = (SimpleEntity) anotherObject;
        return Objects.equals(name, anotherEntity.name)
                && type == anotherEntity.type
                && attackPower == anotherEntity.attackPower;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type, attackPower);
    }

    @Override
    public String toString() {
        return "SimpleEntity{"
                + "name='" + name + '\''
                + ", type=" + type
                + ", health=" + health
                + ", attackPower=" + attackPower
                + ", defended=" + defended
                + '}';
    }
}
