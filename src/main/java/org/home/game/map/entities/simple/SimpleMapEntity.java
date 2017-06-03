package org.home.game.map.entities.simple;

import org.home.game.map.entities.MapEntity;
import org.home.game.map.entities.MapEntityType;

import javax.annotation.Nonnull;
import java.util.Objects;

public class SimpleMapEntity implements MapEntity {

    private final String name;

    private final MapEntityType type;

    public SimpleMapEntity(@Nonnull String name, @Nonnull MapEntityType type) {
        this.name = name;
        this.type = type;
    }

    @Nonnull
    public String getName() {
        return name;
    }

    public boolean isUser() {
        return false;
    }

    @Override
    public void take(@Nonnull MapEntity anotherEntity) {
        throw new UnsupportedOperationException("This method is not supported.");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("This method is not supported.");
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
        SimpleMapEntity that = (SimpleMapEntity) o;
        return Objects.equals(name, that.name)
                && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type);
    }

    @Override
    public String toString() {
        return "SimpleMapEntity{"
                + "name='" + name + '\''
                + ", type=" + type
                + '}';
    }
}
