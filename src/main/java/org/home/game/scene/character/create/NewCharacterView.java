package org.home.game.scene.character.create;

import org.home.game.common.mvp.View;
import org.home.game.scene.character.domain.Race;
import org.home.game.scene.character.domain.Sex;

import javax.annotation.Nonnull;

public interface NewCharacterView extends View<NewCharacterView.ActionDelegate> {

    interface ActionDelegate {
        void onChosen(@Nonnull Race race);

        void onChosen(@Nonnull Sex sex);

        void onChosen(@Nonnull String name);

        void onCompleted();
    }
}
