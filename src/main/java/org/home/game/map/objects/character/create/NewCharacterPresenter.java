package org.home.game.map.objects.character.create;

import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.home.game.common.mvp.AbstractPresenter;
import org.home.game.map.objects.character.GameCharacter;
import org.home.game.map.objects.character.Race;
import org.home.game.map.objects.character.Sex;

import javax.annotation.Nonnull;

import static lombok.AccessLevel.PRIVATE;
import static org.home.game.map.objects.character.GameCharacter.userCharacter;

@FieldDefaults(level = PRIVATE)
public class NewCharacterPresenter extends AbstractPresenter<NewCharacterView> implements NewCharacterView.ActionDelegate {

    Race race;

    Sex sex;

    String name;

    @Getter
    GameCharacter gameCharacter;

    public NewCharacterPresenter(@Nonnull NewCharacterView view) {
        super(view);
        view.setDelegate(this);
    }

    @Override
    public void onChosen(@Nonnull Race race) {
        this.race = race;
    }

    @Override
    public void onChosen(@Nonnull Sex sex) {
        this.sex = sex;
    }

    @Override
    public void onChosen(@Nonnull String name) {
        this.name = name;
    }

    @Override
    public void onCompleted() {
        gameCharacter = userCharacter(name, race, sex);
        name = null;
        race = null;
        sex = null;
    }
}
