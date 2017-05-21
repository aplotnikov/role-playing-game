package org.home.game.map.factory;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.home.game.map.Map;
import org.home.game.map.objects.character.GameCharacter;
import org.home.game.map.objects.character.create.NewCharacterPresenter;

import javax.annotation.Nonnull;

import static lombok.AccessLevel.PRIVATE;
import static org.home.game.map.Map.map;
import static org.home.game.map.objects.animals.Bear.bear;
import static org.home.game.map.objects.animals.Wolf.wolf;
import static org.home.game.map.objects.character.GameCharacter.character;
import static org.home.game.map.objects.character.Race.ORC;
import static org.home.game.map.objects.character.Sex.FEMALE;
import static org.home.game.map.objects.container.Road.road;
import static org.home.game.map.objects.immovable.Stone.stone;
import static org.home.game.map.objects.immovable.Tree.tree;

@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class StaticMapFactory implements MapFactory {

    @NonNull
    NewCharacterPresenter newCharacterPresenter;

    @Nonnull
    @Override
    public Map create() {
        newCharacterPresenter.show();
        GameCharacter userGameCharacter = newCharacterPresenter.getGameCharacter();
        return map()
                .line(road(), road(), wolf(), tree(), stone())
                .line(road(), road(), road(), tree(), tree())
                .line(road(), road(), road(userGameCharacter), road(), bear())
                .line(road(), stone(), road(), road(), road())
                .line(character(ORC.toString(), ORC, FEMALE), tree(), road(), road(), road())
                .create();
    }
}
