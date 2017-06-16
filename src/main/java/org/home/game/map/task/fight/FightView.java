package org.home.game.map.task.fight;

import org.home.game.common.mvp.View;

public interface FightView extends View<FightView.ActionDelegate> {

    interface ActionDelegate {
        void onUserAttack();

        void onUserDefend();
    }
}
