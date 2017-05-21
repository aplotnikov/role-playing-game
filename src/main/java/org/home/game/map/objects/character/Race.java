package org.home.game.map.objects.character;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Race {
    HUMAN("Human"),
    ORC("Orc"),
    ELF("Elf"),
    GNOME("Gnome"),
    TROLL("Troll");

    @NonNull
    private final String title;

    @Override
    public String toString() {
        return title;
    }
}
