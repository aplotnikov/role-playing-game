package org.home.game.map.objects;

import javax.annotation.Nonnull;

public interface MapObject {

    @Nonnull
    String getName();

    boolean isUser();

    void draw();

    boolean canContainAnotherObject();

    boolean containAnotherObject();

    void take(@Nonnull MapObject anotherObject);

    void clear();
}
