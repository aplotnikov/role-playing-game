package org.home.game.character.create;

import lombok.experimental.FieldDefaults;
import org.home.game.character.domain.Race;
import org.home.game.character.domain.Sex;
import org.home.game.common.mvp.AbstractPresenter;

import javax.annotation.Nonnull;

import static lombok.AccessLevel.PRIVATE;

@FieldDefaults(level = PRIVATE)
public class NewCharacterPresenter extends AbstractPresenter<NewCharacterView> implements NewCharacterView.ActionDelegate {

    Race race;

    Sex sex;

    String name;

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

    }
}
