package org.home.game.map.entities.character.create;

import org.home.game.common.mvp.AbstractPresenter;
import org.home.game.map.entities.Entity;
import org.home.game.map.entities.character.Race;
import org.home.game.map.entities.character.Sex;

import static java.util.Objects.requireNonNull;
import static org.home.game.map.entities.EntityFactory.userCharacter;
import static org.home.game.map.entities.character.create.NewCharacterView.ActionDelegate;

public class NewCharacterPresenter extends AbstractPresenter<NewCharacterView> implements NewCharacterFactory, ActionDelegate {

    private Race race;

    private Sex sex;

    private String name;

    public NewCharacterPresenter(NewCharacterView view) {
        super(view);
        view.setDelegate(this);
    }

    @Override
    public void onChosen(Race race) {
        this.race = race;
    }

    @Override
    public void onChosen(Sex sex) {
        this.sex = sex;
    }

    @Override
    public void onChosen(String name) {
        this.name = name;
    }

    @Override
    public void onCompleted() {
        requireNonNull(name, "It is impossible to create an instance of character without name parameter");
        requireNonNull(race, "It is impossible to create an instance of character without race parameter");
        requireNonNull(sex, "It is impossible to create an instance of character without sex parameter");
    }

    public Entity getGameCharacter() {
        show();
        return userCharacter(name, race, sex);
    }
}
