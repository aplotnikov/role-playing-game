package org.home.game.play;

import org.home.game.map.factory.MapFactory;

import javax.annotation.Nonnull;

public class GameFactoryImpl implements GameFactory {

    private final MapFactory mapFactory;

    private final GameView view;

    public GameFactoryImpl(@Nonnull MapFactory mapFactory, @Nonnull GameView view) {
        this.mapFactory = mapFactory;
        this.view = view;
    }

    @Nonnull
    @Override
    public Game create() {
        return new GameImpl(mapFactory.create(), view);
    }

    @Nonnull
    @Override
    public Game resume() {
        // provide correct implementation
        return create();
    }
}
