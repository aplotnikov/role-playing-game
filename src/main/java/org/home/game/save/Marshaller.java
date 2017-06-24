package org.home.game.save;

import org.home.game.map.entities.Entity;

import java.util.List;

public interface Marshaller {
    void marshall(List<List<Entity>> entities, String path);

    List<List<Entity>> unmarshall(String path);
}
