package org.home.game;

import org.home.game.common.mvp.Presenter;
import org.home.game.map.entities.character.create.NewCharacterConsoleConsoleView;
import org.home.game.map.entities.character.create.NewCharacterPresenter;
import org.home.game.map.factory.MapFactory;
import org.home.game.map.factory.StaticMapFactory;
import org.home.game.map.painter.ConsoleMapPainter;
import org.home.game.map.painter.MapPainter;
import org.home.game.menu.MainMainMenuConsoleConsoleView;
import org.home.game.menu.MainMenuPresenter;
import org.home.game.play.GameFactory;

import javax.annotation.Nonnull;

class EntryPointFactory {
    @Nonnull
    static Presenter newEntryPoint() {
        return new MainMenuPresenter(new MainMainMenuConsoleConsoleView(), gameFactory());
    }

    @Nonnull
    private static GameFactory gameFactory() {
        return new GameFactory(mapFactory(), mapPainter());
    }

    @Nonnull
    private static MapFactory mapFactory() {
        return new StaticMapFactory(newCharacterPresenter());
    }

    @Nonnull
    private static NewCharacterPresenter newCharacterPresenter() {
        return new NewCharacterPresenter(new NewCharacterConsoleConsoleView());
    }

    @Nonnull
    private static MapPainter mapPainter() {
        return new ConsoleMapPainter();
    }
}
