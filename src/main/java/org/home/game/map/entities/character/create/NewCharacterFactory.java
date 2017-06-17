package org.home.game.map.entities.character.create;

import org.home.game.map.entities.Entity;

import javax.annotation.Nonnull;

public interface NewCharacterFactory {
    @Nonnull
    Entity getGameCharacter();
}
