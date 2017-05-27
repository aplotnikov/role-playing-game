package org.home.game.map.entities.character;

import javax.annotation.Nonnull;

public enum Race {
    HUMAN("Human"),
    ORC("Orc"),
    ELF("Elf"),
    GNOME("Gnome"),
    TROLL("Troll");

    private final String title;

    Race(@Nonnull String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
