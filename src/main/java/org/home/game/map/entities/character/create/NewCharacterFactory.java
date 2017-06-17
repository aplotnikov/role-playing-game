package org.home.game.map.entities.character.create;

import org.home.game.map.entities.MapEntity;

import javax.annotation.Nonnull;

public interface NewCharacterFactory {
    @Nonnull
    MapEntity getGameCharacter();
}
