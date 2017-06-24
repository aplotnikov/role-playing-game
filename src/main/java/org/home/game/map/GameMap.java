package org.home.game.map;

import org.home.game.map.entities.Entity;

import java.util.List;

public interface GameMap {

    boolean containsUserCharacter();

    boolean containsTasks();

    void goToNextIteration();

    List<List<Entity>> getEntities();
}
