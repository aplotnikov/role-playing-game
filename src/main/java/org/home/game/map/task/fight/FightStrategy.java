package org.home.game.map.task.fight;

import org.home.game.common.mvp.AbstractPresenter;
import org.home.game.map.entities.Entity;
import org.home.game.map.task.TaskCompletionStrategy;

public class FightStrategy extends AbstractPresenter<FightView> implements TaskCompletionStrategy, FightView.ActionDelegate {

    private Entity user;

    private Entity enemy;

    public FightStrategy(FightView view) {
        super(view);
        this.view.setDelegate(this);
    }

    @Override
    public void complete(Entity user, Entity enemy) {
        this.user = user;
        this.enemy = enemy;
        nextIteration();
    }

    private void nextIteration() {
        if (user.isAlive() && enemy.isAlive()) {
            view.drawUser(user);
            view.drawEnemy(enemy);
            show();
        }
    }

    @Override
    public void onUserAttack() {
        view.drawAttack(user, enemy, enemy.isBeatenBy(user));
        view.drawAttack(enemy, user, user.isBeatenBy(enemy));
        nextIteration();
    }

    @Override
    public void onUserDefend() {
        user.defense();
        view.drawAttack(enemy, user, user.isBeatenBy(enemy));
        nextIteration();
    }

    @Override
    public void onDoNothing() {
        view.drawAttack(enemy, user, user.isBeatenBy(enemy));
        nextIteration();
    }
}
