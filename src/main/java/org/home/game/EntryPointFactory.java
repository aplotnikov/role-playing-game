package org.home.game;

import org.home.game.common.mvp.Presenter;
import org.home.game.map.factory.MapFactory;
import org.home.game.map.factory.StaticMapFactory;
import org.home.game.map.objects.character.create.NewCharacterConsoleConsoleView;
import org.home.game.map.objects.character.create.NewCharacterPresenter;
import org.home.game.menu.MenuConsoleConsoleView;
import org.home.game.menu.MenuPresenter;
import org.home.game.play.GameFactory;

import javax.annotation.Nonnull;

class EntryPointFactory {
    @Nonnull
    static Presenter newEntryPoint() {
        return new MenuPresenter(new MenuConsoleConsoleView(), gameFactory());
    }

    @Nonnull
    private static GameFactory gameFactory() {
        return new GameFactory(mapFactory());
    }

    @Nonnull
    private static MapFactory mapFactory() {
        return new StaticMapFactory(newCharacterPresenter());
    }

    @Nonnull
    private static NewCharacterPresenter newCharacterPresenter() {
        return new NewCharacterPresenter(new NewCharacterConsoleConsoleView());
    }
}
