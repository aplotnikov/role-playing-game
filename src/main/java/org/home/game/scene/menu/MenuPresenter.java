package org.home.game.scene.menu;

import lombok.experimental.FieldDefaults;
import org.home.game.common.mvp.AbstractPresenter;
import org.home.game.scene.character.create.NewCharacterPresenter;

import javax.annotation.Nonnull;

import static lombok.AccessLevel.PRIVATE;

@FieldDefaults(level = PRIVATE, makeFinal = true)
public class MenuPresenter extends AbstractPresenter<MenuView> implements MenuView.ActionDelegate {

    NewCharacterPresenter newCharacterPresenter;

    public MenuPresenter(@Nonnull MenuView view, @Nonnull NewCharacterPresenter newCharacterPresenter) {
        super(view);
        this.newCharacterPresenter = newCharacterPresenter;
        this.view.setDelegate(this);
    }

    @Override
    public void onStartChosen() {
        newCharacterPresenter.show();
    }

    @Override
    public void onResumeChosen() {

    }
}
