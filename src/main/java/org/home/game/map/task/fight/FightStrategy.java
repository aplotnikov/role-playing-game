package org.home.game.map.task.fight;

import org.home.game.common.mvp.AbstractPresenter;
import org.home.game.map.entities.MapEntity;
import org.home.game.map.task.TaskCompletionStrategy;

import javax.annotation.Nonnull;

public class FightStrategy extends AbstractPresenter<FightView> implements TaskCompletionStrategy, FightView.ActionDelegate {

    private MapEntity user;

    private MapEntity enemy;

    public FightStrategy(@Nonnull FightView view) {
        super(view);
        this.view.setDelegate(this);
    }

    @Override
    public void complete(@Nonnull MapEntity user, @Nonnull MapEntity enemy) {
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
