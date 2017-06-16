package org.home.game.map.task;

import org.home.game.map.entities.MapEntity;

import javax.annotation.Nonnull;

public interface TaskCompletionStrategy {
    void complete(@Nonnull MapEntity userEntity, @Nonnull MapEntity taskEntity);
}
