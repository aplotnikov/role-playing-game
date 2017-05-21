package org.home.game.map.objects.animals;

import org.home.game.map.objects.AbstractMapObject;

import javax.annotation.Nonnull;

public class Bear extends AbstractMapObject {

    private Bear() {
        super("Bear", false);
    }

    @Nonnull
    public static Bear bear() {
        return new Bear();
    }
}
