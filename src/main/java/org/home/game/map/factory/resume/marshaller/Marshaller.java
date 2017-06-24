package org.home.game.map.factory.resume.marshaller;

import org.home.game.map.entities.Entity;

import java.util.List;

public interface Marshaller {
    void marshall(List<List<Entity>> entities, String path);

    List<List<Entity>> unmarshall(String path);
}
