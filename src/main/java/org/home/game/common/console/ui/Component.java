package org.home.game.common.console.ui;

import static java.util.stream.IntStream.rangeClosed;

public interface Component {

    void draw();

    default void redraw() {
        erase();
        draw();
    }

    default void erase() {
        rangeClosed(1, 50).forEach(value -> System.out.println());
    }
}
