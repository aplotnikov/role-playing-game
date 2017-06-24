package org.home.game.map.factory.resume;

import org.home.game.common.console.ui.TextBox;
import org.home.game.common.mvp.console.AbstractConsoleView;

public class RestoreConsoleView extends AbstractConsoleView implements RestoreView {
    private final TextBox path = new TextBox("Type file location: ");

    @Override
    public void draw() {
        path.draw();
    }

    @Override
    public String getPath() {
        return path.getValue();
    }
}
