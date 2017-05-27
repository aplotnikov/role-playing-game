package org.home.game.play;

import org.home.game.map.factory.MapFactory;
import org.home.game.map.painter.MapPainter;

import javax.annotation.Nonnull;

public class GameFactory {

    private final MapFactory mapFactory;

    private final MapPainter mapPainter;

    public GameFactory(@Nonnull MapFactory mapFactory, @Nonnull MapPainter mapPainter) {
        this.mapFactory = mapFactory;
        this.mapPainter = mapPainter;
    }

    @Nonnull
    public Game create() {
        return new Game(mapFactory.create(), mapPainter);
    }

    @Nonnull
    public Game resume() {
        // provide correct implementation
        return create();
    }
}
