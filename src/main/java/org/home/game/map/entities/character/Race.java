package org.home.game.map.entities.character;

public enum Race {
    HUMAN("Human"),
    ORC("Orc"),
    ELF("Elf"),
    GNOME("Gnome"),
    TROLL("Troll");

    private final String title;

    Race(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
