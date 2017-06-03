package org.home.game.map.factory;

import org.home.game.map.GameMap;
import org.home.game.map.MainGameMap;
import org.home.game.map.behaviour.GameStrategy;
import org.home.game.map.entities.MapEntity;
import org.home.game.map.entities.character.create.NewCharacterPresenter;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.function.Predicate;

import static org.home.game.map.GameMapBuilder.map;
import static org.home.game.map.entities.MapEntityFactory.bear;
import static org.home.game.map.entities.MapEntityFactory.character;
import static org.home.game.map.entities.MapEntityFactory.road;
import static org.home.game.map.entities.MapEntityFactory.stone;
import static org.home.game.map.entities.MapEntityFactory.tree;
import static org.home.game.map.entities.MapEntityFactory.wolf;
import static org.home.game.map.entities.character.Race.ORC;
import static org.home.game.map.entities.character.Sex.FEMALE;

public class StaticMapFactory implements MapFactory {

    private final NewCharacterPresenter newCharacterPresenter;

    private final GameStrategy userBehaviour;

    private final GameStrategy gameBehaviour;

    private final Predicate<MapEntity> taskDetectionCondition;

    public StaticMapFactory(
            @Nonnull NewCharacterPresenter newCharacterPresenter,
            @Nonnull GameStrategy userBehaviour,
            @Nonnull GameStrategy gameBehaviour,
            @Nonnull Predicate<MapEntity> taskDetectionCondition
    ) {
        this.newCharacterPresenter = newCharacterPresenter;
        this.userBehaviour = userBehaviour;
        this.gameBehaviour = gameBehaviour;
        this.taskDetectionCondition = taskDetectionCondition;
    }

    @Nonnull
    @Override
    public GameMap create() {
        newCharacterPresenter.show();
        MapEntity character = newCharacterPresenter.getGameCharacter()
                                                   .orElseThrow(() -> new IllegalStateException("User character is not created"));
        return new MainGameMap(entities(character), userBehaviour, gameBehaviour, taskDetectionCondition);
    }

    @Nonnull
    private List<List<MapEntity>> entities(@Nonnull MapEntity character) {
        return map()
                .line(road(), road(), wolf(), tree(), stone())
                .line(road(), road(), road(), tree(), tree())
                .line(road(), road(), road(character), road(), bear())
                .line(road(), stone(), road(), road(), road())
                .line(road(orc()), tree(), road(), road(), road())
                .create();
    }

    @Nonnull
    private MapEntity orc() {
        return character(ORC.toString(), ORC, FEMALE);
    }
}
