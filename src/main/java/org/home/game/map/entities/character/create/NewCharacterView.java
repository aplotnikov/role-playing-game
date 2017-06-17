package org.home.game.map.entities.character.create;

import org.home.game.common.mvp.View;
import org.home.game.map.entities.character.Race;
import org.home.game.map.entities.character.Sex;

import javax.annotation.Nonnull;

public interface NewCharacterView extends View<NewCharacterView.ActionDelegate> {
    interface ActionDelegate {
        void onChosen(@Nonnull Race race);

        void onChosen(@Nonnull Sex sex);

        void onChosen(@Nonnull String name);

        void onCompleted();
    }
}
