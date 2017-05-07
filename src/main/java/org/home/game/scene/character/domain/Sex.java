package org.home.game.scene.character.domain;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Sex {
    MALE("Male"),
    FEMALE("Female");

    @NonNull
    private final String title;

    @Override
    public String toString() {
        return title;
    }
}
