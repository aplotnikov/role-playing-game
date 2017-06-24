package org.home.game.map.task.fight;

import org.home.game.common.mvp.View;
import org.home.game.map.entities.Entity;

public interface FightView extends View<FightView.ActionDelegate> {

    void drawUser(Entity user);

    void drawEnemy(Entity enemy);

    void drawAttack(Entity attacker, Entity defender, int damage);

    interface ActionDelegate {
        void onUserAttack();

        void onUserDefend();

        void onDoNothing();
    }
}
