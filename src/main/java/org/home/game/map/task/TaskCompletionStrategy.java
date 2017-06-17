package org.home.game.map.task;

import org.home.game.map.entities.Entity;

import javax.annotation.Nonnull;

public interface TaskCompletionStrategy {
    void complete(@Nonnull Entity user, @Nonnull Entity task);
}
