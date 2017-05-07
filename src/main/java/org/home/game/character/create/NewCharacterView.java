package org.home.game.character.create;

import org.home.game.character.domain.Race;
import org.home.game.character.domain.Sex;
import org.home.game.common.mvp.View;

import javax.annotation.Nonnull;

public interface NewCharacterView extends View<NewCharacterView.ActionDelegate> {

    interface ActionDelegate {
        void onChosen(@Nonnull Race race);

        void onChosen(@Nonnull Sex sex);

        void onChosen(@Nonnull String name);

        void onCompleted();
    }
}
