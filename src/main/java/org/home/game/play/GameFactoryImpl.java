package org.home.game.play;

import org.home.game.map.GameMap;
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
        return create(mapFactory.create());
    }

    @Override
    public Game resume() {
        return create(mapFactory.restore());
    }

    private Game create(GameMap map) {
        return new GameImpl(map, view);
    }
}
