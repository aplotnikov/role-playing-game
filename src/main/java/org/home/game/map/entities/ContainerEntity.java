package org.home.game.map.entities;

import java.util.Optional;

public class ContainerEntity extends SimpleEntity {

    private Entity innerEntity;

    public ContainerEntity(String name, EntityType type, int attachPower) {
        super(name, type, attachPower);
    }

    @Override
    public boolean canContainAnotherEntity() {
        return true;
    }

    @Override
    public Optional<Entity> getInnerEntity() {
        return Optional.ofNullable(innerEntity);
    }

    @Override
    public void take(Entity anotherEntity) {
        innerEntity = anotherEntity;
    }

    @Override
    public void clear() {
        innerEntity = null;
    }

    @Override
    public String toString() {
        return "ContainerEntity{"
                + "name='" + getName() + '\''
                + ", type=" + getType()
                + ", health=" + getHealth()
                + ", attackPower=" + getAttackPower()
                + ", innerEntity=" + innerEntity
                + ", defended=" + isDefended()
                + '}';
    }
}
