package org.home.game.map;

import org.home.game.map.entities.MapEntity;

import javax.annotation.Nonnull;
import java.util.List;

public interface GameMap {

    boolean containsUserCharacter();

    boolean containsTasks();

    void goToNextIteration();

    @Nonnull
    List<List<MapEntity>> getEntities();
}
