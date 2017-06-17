package org.home.game.map.entities;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Optional;

public class ContainerEntity extends SimpleEntity {

    @CheckForNull
    private Entity innerEntity;

    public ContainerEntity(@Nonnull String name, @Nonnull EntityType type, @Nonnegative int attachPower) {
        super(name, type, attachPower);
    }

    @Override
    public boolean canContainAnotherEntity() {
        return true;
    }

    @Nonnull
    @Override
    public Optional<Entity> getInnerEntity() {
        return Optional.ofNullable(innerEntity);
    }

    @Override
    public void take(@Nonnull Entity anotherEntity) {
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
