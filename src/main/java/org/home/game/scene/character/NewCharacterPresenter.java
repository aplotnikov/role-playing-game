package org.home.game.scene.character;

import lombok.experimental.FieldDefaults;
import org.home.game.common.mvp.AbstractPresenter;
import org.home.game.scene.character.NewCharacterView.ActionDelegate;
import org.home.game.scene.character.domain.Race;
import org.home.game.scene.character.domain.Sex;

import javax.annotation.Nonnull;

import static lombok.AccessLevel.PRIVATE;

@FieldDefaults(level = PRIVATE)
public class NewCharacterPresenter extends AbstractPresenter<NewCharacterView> implements ActionDelegate {

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
