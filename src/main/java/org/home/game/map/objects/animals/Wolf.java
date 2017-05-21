package org.home.game.map.objects.animals;

import org.home.game.map.objects.AbstractMapObject;

import javax.annotation.Nonnull;

public class Wolf extends AbstractMapObject {
    private Wolf() {
        super("Wolf", false);
    }

    @Nonnull
    public static Wolf wolf() {
        return new Wolf();
    }
}
