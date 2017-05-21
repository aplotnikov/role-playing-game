package org.home.game.map.objects.immovable;

import org.home.game.map.objects.AbstractMapObject;

import javax.annotation.Nonnull;

public class Tree extends AbstractMapObject {
    private Tree() {
        super("Tree", false);
    }

    @Nonnull
    public static Tree tree() {
        return new Tree();
    }
}
