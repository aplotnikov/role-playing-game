package org.home.game.map.task;

import org.home.game.map.entities.Entity;

public interface TaskCompletionStrategy {
    void complete(Entity user, Entity task);
}
