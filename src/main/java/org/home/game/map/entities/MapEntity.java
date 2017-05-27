package org.home.game.map.entities;

import javax.annotation.Nonnull;
import java.util.Optional;

import static java.util.Optional.empty;

public interface MapEntity {

    @Nonnull
    String getName();

    boolean isUser();

    boolean canContainAnotherEntity();

    boolean containAnotherEntity();

    void take(@Nonnull MapEntity anotherEntity);

    void clear();

    default Optional<MapEntity> getInnerEntity() {
        return empty();
    }

    @Nonnull
    MapEntityType getType();

}
