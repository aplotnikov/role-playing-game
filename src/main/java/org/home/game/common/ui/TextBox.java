package org.home.game.common.ui;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.home.game.common.ui.utils.ConsoleReader;

import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class TextBox implements Component {

    ConsoleReader reader = new ConsoleReader();

    @NonNull
    String title;

    @Override
    public void draw() {
        System.out.print(title);
    }

    public String getValue() {
        return reader.readString();
    }
}
