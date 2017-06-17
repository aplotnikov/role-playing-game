package org.home.game.map.factory;

import org.home.game.map.GameMap;
import org.home.game.map.MainGameMap;
import org.home.game.map.behaviour.user.UserMovementInput;
import org.home.game.map.entities.Entity;
import org.home.game.map.entities.character.create.NewCharacterFactory;
import org.home.game.map.task.TaskCompletionStrategy;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.function.Predicate;

import static org.home.game.map.GameMapBuilder.map;
import static org.home.game.map.entities.EntityFactory.bear;
import static org.home.game.map.entities.EntityFactory.character;
import static org.home.game.map.entities.EntityFactory.road;
import static org.home.game.map.entities.EntityFactory.stone;
import static org.home.game.map.entities.EntityFactory.tree;
import static org.home.game.map.entities.EntityFactory.wolf;
import static org.home.game.map.entities.character.Race.ORC;
import static org.home.game.map.entities.character.Sex.FEMALE;

public class StaticMapFactory implements MapFactory {

    private final NewCharacterFactory newCharacterFactory;

    private final UserMovementInput userMovementInput;

    private final Predicate<Entity> taskDetectionCondition;

    private final TaskCompletionStrategy taskCompletionStrategy;

    public StaticMapFactory(@Nonnull NewCharacterFactory newCharacterFactory,
                            @Nonnull UserMovementInput userMovementInput,
                            @Nonnull Predicate<Entity> taskDetectionCondition,
                            @Nonnull TaskCompletionStrategy taskCompletionStrategy) {
        this.newCharacterFactory = newCharacterFactory;
        this.userMovementInput = userMovementInput;
        this.taskDetectionCondition = taskDetectionCondition;
        this.taskCompletionStrategy = taskCompletionStrategy;
    }

    @Nonnull
    @Override
    public GameMap create() {
        Entity character = newCharacterFactory.getGameCharacter();
        return new MainGameMap(entities(character), userMovementInput, taskDetectionCondition, taskCompletionStrategy);
    }

    @Nonnull
    private List<List<Entity>> entities(@Nonnull Entity character) {
        return map()
                .line(road(), road(), road(wolf()), tree(), stone())
                .line(road(), road(), road(), tree(), tree())
                .line(road(), road(), road(character), road(), road(bear()))
                .line(road(), stone(), road(), road(), road())
                .line(road(orc()), tree(), road(), road(), road())
                .create();
    }

    @Nonnull
    private Entity orc() {
        return character(ORC.toString(), ORC, FEMALE);
    }
}
