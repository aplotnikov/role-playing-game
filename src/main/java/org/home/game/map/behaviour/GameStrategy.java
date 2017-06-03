package org.home.game.map.behaviour;

import org.home.game.map.entities.MapEntity;

import javax.annotation.Nonnull;
import java.util.List;

public interface GameStrategy {
    void process(@Nonnull List<List<MapEntity>> entities);
}
