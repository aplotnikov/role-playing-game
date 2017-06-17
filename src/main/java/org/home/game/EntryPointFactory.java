package org.home.game;

import org.home.game.common.mvp.Presenter;
import org.home.game.map.behaviour.user.UserMovementConsoleInput;
import org.home.game.map.behaviour.user.UserMovementInput;
import org.home.game.map.entities.Entity;
import org.home.game.map.entities.EntityType;
import org.home.game.map.entities.character.create.NewCharacterConsoleConsoleView;
import org.home.game.map.entities.character.create.NewCharacterPresenter;
import org.home.game.map.factory.MapFactory;
import org.home.game.map.factory.StaticMapFactory;
import org.home.game.map.painter.ConsoleMapPainter;
import org.home.game.map.painter.MapPainter;
import org.home.game.map.task.TaskCompletionStrategy;
import org.home.game.map.task.fight.FightConsoleView;
import org.home.game.map.task.fight.FightStrategy;
import org.home.game.menu.MainMainMenuConsoleConsoleView;
import org.home.game.menu.MainMenuPresenter;
import org.home.game.play.GameConsoleView;
import org.home.game.play.GameFactory;
import org.home.game.play.GameFactoryImpl;
import org.home.game.play.GameView;

import javax.annotation.Nonnull;
import java.util.EnumSet;
import java.util.Set;
import java.util.function.Predicate;

import static org.home.game.map.entities.EntityType.BEAR;
import static org.home.game.map.entities.EntityType.CHARACTER;
import static org.home.game.map.entities.EntityType.WOLF;

class EntryPointFactory {
    @Nonnull
    static Presenter newEntryPoint() {
        return new MainMenuPresenter(new MainMainMenuConsoleConsoleView(), gameFactory());
    }

    @Nonnull
    private static GameFactory gameFactory() {
        return new GameFactoryImpl(mapFactory(), mapPainter(), gameView());
    }

    @Nonnull
    private static MapPainter mapPainter() {
        return new ConsoleMapPainter();
    }

    @Nonnull
    private static MapFactory mapFactory() {
        return new StaticMapFactory(characterPresenter(), userMovementInput(), taskDetectionCondition(), taskCompletionStrategy());
    }

    @Nonnull
    private static NewCharacterPresenter characterPresenter() {
        return new NewCharacterPresenter(new NewCharacterConsoleConsoleView());
    }

    @Nonnull
    private static UserMovementInput userMovementInput() {
        return new UserMovementConsoleInput();
    }

    @Nonnull
    private static Predicate<Entity> taskDetectionCondition() {
        Set<EntityType> enemies = EnumSet.of(CHARACTER, WOLF, BEAR);
        return entity -> enemies.contains(entity.getType());
    }

    @Nonnull
    private static TaskCompletionStrategy taskCompletionStrategy() {
        return new FightStrategy(new FightConsoleView());
    }

    @Nonnull
    private static GameView gameView() {
        return new GameConsoleView();
    }
}
