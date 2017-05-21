package org.home.game.play;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.home.game.map.factory.MapFactory;

import javax.annotation.Nonnull;

import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class GameFactory {

    MapFactory mapFactory;

    @Nonnull
    public Game create() {
        return new Game(mapFactory.create());
    }

    @Nonnull
    public Game resume() {
        // provide correct implementation
        return create();
    }
}
