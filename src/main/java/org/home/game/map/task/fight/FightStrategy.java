package org.home.game.map.task.fight;

import org.home.game.common.mvp.AbstractPresenter;
import org.home.game.map.entities.Entity;
import org.home.game.map.task.TaskCompletionStrategy;

import javax.annotation.Nonnull;

public class FightStrategy extends AbstractPresenter<FightView> implements TaskCompletionStrategy, FightView.ActionDelegate {

    private Entity user;

    private Entity enemy;

    public FightStrategy(@Nonnull FightView view) {
        super(view);
        this.view.setDelegate(this);
    }

    @Override
    public void complete(@Nonnull Entity user, @Nonnull Entity enemy) {
        this.user = user;
        this.enemy = enemy;
        nextIteration();
    }

    private void nextIteration() {
        if (user.isAlive() && enemy.isAlive()) {
            show();
        }
    }

    @Override
    public void onUserAttack() {
        enemy.isBeatenBy(user);
        user.isBeatenBy(enemy);
        nextIteration();
    }

    @Override
    public void onUserDefend() {
        user.defense();
        user.isBeatenBy(enemy);
        nextIteration();
    }

    @Override
    public void onDoNothing() {
        user.isBeatenBy(enemy);
        nextIteration();
    }
}
