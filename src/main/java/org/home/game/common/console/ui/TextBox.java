package org.home.game.common.console.ui;

import org.home.game.common.console.ui.utils.ConsoleReader;

import javax.annotation.Nonnull;

public class TextBox implements Component {

    private final ConsoleReader reader;

    private final String title;

    public TextBox(@Nonnull String title) {
        this.reader = new ConsoleReader();
        this.title = title;
    }

    @Override
    public void draw() {
        System.out.println(title);
    }

    public String getValue() {
        return reader.readString();
    }
}
