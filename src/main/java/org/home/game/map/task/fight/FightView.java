package org.home.game.map.task.fight;

import org.home.game.common.mvp.View;
import org.home.game.map.entities.Entity;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

public interface FightView extends View<FightView.ActionDelegate> {

    void drawUser(@Nonnull Entity user);

    void drawEnemy(@Nonnull Entity enemy);

    void drawAttack(@Nonnull Entity attacker, @Nonnull Entity defender, @Nonnegative int damage);

    interface ActionDelegate {
        void onUserAttack();

        void onUserDefend();

        void onDoNothing();
    }
}
