package org.home.game.map.entities.character.create;

import org.home.game.common.mvp.AbstractPresenter;
import org.home.game.map.entities.MapEntity;
import org.home.game.map.entities.character.Race;
import org.home.game.map.entities.character.Sex;

import javax.annotation.Nonnull;

import static java.util.Objects.requireNonNull;
import static org.home.game.map.entities.MapEntityFactory.userCharacter;
import static org.home.game.map.entities.character.create.NewCharacterView.ActionDelegate;

public class NewCharacterPresenter extends AbstractPresenter<NewCharacterView> implements NewCharacterFactory, ActionDelegate {

    private Race race;

    private Sex sex;

    private String name;

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
        requireNonNull(name, "It is impossible to create an instance of character without name parameter");
        requireNonNull(race, "It is impossible to create an instance of character without race parameter");
        requireNonNull(sex, "It is impossible to create an instance of character without sex parameter");
    }

    @Nonnull
    public MapEntity getGameCharacter() {
        show();
        return userCharacter(name, race, sex);
    }
}
