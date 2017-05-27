package org.home.game.map.entities.character;

import javax.annotation.Nonnull;

public enum Sex {
    MALE("Male"),
    FEMALE("Female");

    private final String title;

    Sex(@Nonnull String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
