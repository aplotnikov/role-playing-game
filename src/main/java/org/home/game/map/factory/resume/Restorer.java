package org.home.game.map.factory.resume;

import org.home.game.map.entities.Entity;

import java.util.List;

public interface Restorer {
    List<List<Entity>> restore();
}
