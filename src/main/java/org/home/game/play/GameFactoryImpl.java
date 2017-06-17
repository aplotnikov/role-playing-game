package org.home.game.play;

import org.home.game.map.factory.MapFactory;
import org.home.game.map.painter.MapPainter;

import javax.annotation.Nonnull;

public class GameFactoryImpl implements GameFactory {

    private final MapFactory mapFactory;

    private final MapPainter mapPainter;

    public GameFactoryImpl(@Nonnull MapFactory mapFactory, @Nonnull MapPainter mapPainter) {
        this.mapFactory = mapFactory;
        this.mapPainter = mapPainter;
    }

    @Nonnull
    @Override
    public Game create() {
        return new GameImpl(mapFactory.create(), mapPainter);
    }

    @Nonnull
    @Override
    public Game resume() {
        // provide correct implementation
        return create();
    }
}
