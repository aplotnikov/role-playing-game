package org.home.game.map.entities.container;

import org.home.game.map.entities.MapEntity;
import org.home.game.map.entities.MapEntityType;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.Optional;

import static java.util.Objects.nonNull;

public class ContainerMapEntity implements MapEntity {

    private final String name;

    private final MapEntityType type;

    @CheckForNull
    private MapEntity entity;

    public ContainerMapEntity(@Nonnull String name, @Nonnull MapEntityType type) {
        this.name = name;
        this.type = type;
    }

    @Nonnull
    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isUser() {
        return false;
    }

    @Override
    public boolean canContainAnotherEntity() {
        return true;
    }

    @Override
    public boolean containAnotherEntity() {
        return nonNull(entity);
    }

    @Override
    public Optional<MapEntity> getInnerEntity() {
        return Optional.ofNullable(entity);
    }

    @Override
    public void take(@Nonnull MapEntity anotherEntity) {
        entity = anotherEntity;
    }

    @Override
    public void clear() {
        entity = null;
    }

    @Nonnull
    @Override
    public MapEntityType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ContainerMapEntity that = (ContainerMapEntity) o;
        return Objects.equals(name, that.name)
                && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type);
    }

    @Override
    public String toString() {
        return "ContainerMapEntity{"
                + "name='" + name + '\''
                + ", type=" + type
                + ", entity=" + entity
                + '}';
    }
}
