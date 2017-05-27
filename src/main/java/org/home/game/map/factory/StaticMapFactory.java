package org.home.game.map.factory;

import org.home.game.map.GameMap;
import org.home.game.map.entities.MapEntity;
import org.home.game.map.entities.character.create.NewCharacterPresenter;

import javax.annotation.Nonnull;

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

    public StaticMapFactory(@Nonnull NewCharacterPresenter newCharacterPresenter) {
        this.newCharacterPresenter = newCharacterPresenter;
    }

    @Nonnull
    @Override
    public GameMap create() {
        newCharacterPresenter.show();
        MapEntity character = newCharacterPresenter.getGameCharacter().orElseThrow(IllegalStateException::new);
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
