package org.home.game.map;

import org.home.game.map.entities.Entity;

import javax.annotation.Nonnull;
import java.util.List;

public interface GameMap {

    boolean containsUserCharacter();

    boolean containsTasks();

    void goToNextIteration();

    @Nonnull
    List<List<Entity>> getEntities();
}
