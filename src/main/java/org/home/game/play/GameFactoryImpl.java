package org.home.game.play;

import org.home.game.map.factory.MapFactory;
import org.home.game.map.painter.MapPainter;

import javax.annotation.Nonnull;

public class GameFactoryImpl implements GameFactory {

    private final MapFactory mapFactory;

    private final MapPainter mapPainter;

    private final GameView view;

    public GameFactoryImpl(@Nonnull MapFactory mapFactory, @Nonnull MapPainter mapPainter, @Nonnull GameView view) {
        this.mapFactory = mapFactory;
        this.mapPainter = mapPainter;
        this.view = view;
    }

    @Nonnull
    @Override
    public Game create() {
        return new GameImpl(mapFactory.create(), mapPainter, view);
    }

    @Nonnull
    @Override
    public Game resume() {
        // provide correct implementation
        return create();
    }
}
