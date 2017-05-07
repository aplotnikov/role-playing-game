package org.home.game;

import org.home.game.common.mvp.Presenter;
import org.home.game.scene.character.NewCharacterConsoleConsoleView;
import org.home.game.scene.character.NewCharacterPresenter;
import org.home.game.scene.menu.MenuConsoleConsoleView;
import org.home.game.scene.menu.MenuPresenter;

import javax.annotation.Nonnull;

class EntryPointFactory {
    @Nonnull
    static Presenter newEntryPoint() {
        return new MenuPresenter(new MenuConsoleConsoleView(), newCharacterPresenter());
    }

    @Nonnull
    private static NewCharacterPresenter newCharacterPresenter() {
        return new NewCharacterPresenter(new NewCharacterConsoleConsoleView());
    }
}
