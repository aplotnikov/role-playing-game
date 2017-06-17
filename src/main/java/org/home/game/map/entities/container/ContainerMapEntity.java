package org.home.game.map.entities.container;

import org.home.game.map.entities.MapEntity;
import org.home.game.map.entities.MapEntityType;
import org.home.game.map.entities.simple.SimpleMapEntity;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Optional;

public class ContainerMapEntity extends SimpleMapEntity {

    @CheckForNull
    private MapEntity innerEntity;

    public ContainerMapEntity(@Nonnull String name, @Nonnull MapEntityType type, @Nonnegative int attachPower) {
        super(name, type, attachPower);
    }

    @Override
    public boolean canContainAnotherEntity() {
        return true;
    }

    @Nonnull
    @Override
    public Optional<MapEntity> getInnerEntity() {
        return Optional.ofNullable(innerEntity);
    }

    @Override
    public void take(@Nonnull MapEntity anotherEntity) {
        innerEntity = anotherEntity;
    }

    @Override
    public void clear() {
        innerEntity = null;
    }

    @Override
    public String toString() {
        return "ContainerMapEntity{"
                + "name='" + getName() + '\''
                + ", type=" + getType()
                + ", health=" + getHealth()
                + ", attackPower=" + getAttackPower()
                + ", innerEntity=" + innerEntity
                + ", defended=" + isDefended()
                + '}';
    }
}
