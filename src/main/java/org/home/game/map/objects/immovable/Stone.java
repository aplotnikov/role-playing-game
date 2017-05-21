package org.home.game.map.objects.immovable;

import org.home.game.map.objects.AbstractMapObject;

import javax.annotation.Nonnull;

public class Stone extends AbstractMapObject {

    private Stone() {
        super("Stone", false);
    }

    @Nonnull
    public static Stone stone() {
        return new Stone();
    }
}
