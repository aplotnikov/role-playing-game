package org.home.game.play;

import org.home.game.map.factory.MapFactory;

public class GameFactoryImpl implements GameFactory {

    private final MapFactory mapFactory;

    private final GameView view;

    public GameFactoryImpl(MapFactory mapFactory, GameView view) {
        this.mapFactory = mapFactory;
        this.view = view;
    }

    @Override
    public Game create() {
        return new GameImpl(mapFactory.create(), view);
    }

    @Override
    public Game resume() {
        // provide correct implementation
        return create();
    }
}
