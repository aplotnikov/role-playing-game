package org.home.game.map.entities;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

public class SimpleEntity implements Entity {

    private final String name;

    private final EntityType type;

    private final int attackPower;

    private int health;

    private boolean defended;

    public SimpleEntity(String name, EntityType type, int attackPower) {
        this.name = name;
        this.type = type;
        this.attackPower = attackPower;
        this.health = 100;
        this.defended = false;
    }

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
    public boolean containTasks(Predicate<Entity> condition) {
        return condition.test(this) && !isUser()
                || getInnerEntity().filter(entity -> entity.containTasks(condition)).isPresent();
    }

    @Override
    public Entity findEntity(Predicate<Entity> condition) {
        return getInnerEntity()
                .map(entity -> condition.test(entity) ? entity : entity.findEntity(condition))
                .orElseThrow(() -> new IllegalStateException("There is no entities with such condition"));
    }

    @Override
    public Optional<Entity> getInnerEntity() {
        return Optional.empty();
    }

    @Override
    public void take(Entity anotherEntity) {
        throw new UnsupportedOperationException("This method is not supported.");
    }

    @Override
    public int isBeatenBy(Entity anotherEntity) {
        if (defended) {
            defended = false;
            return 0;
        }

        int anotherEntityAttackPower = anotherEntity.getAttackPower();
        if (health >= anotherEntityAttackPower) {
            health -= anotherEntityAttackPower;
            return anotherEntityAttackPower;
        }

        int oldHealth = health;
        health = 0;
        return oldHealth;
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
        getInnerEntity().ifPresent(Entity::relax);
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
