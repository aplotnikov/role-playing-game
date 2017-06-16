package org.home.game.map.task.fight;

import org.home.game.map.entities.MapEntity;
import org.home.game.map.task.TaskCompletionStrategy;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

import static java.util.Objects.requireNonNull;

public class FightStrategy implements TaskCompletionStrategy, FightView.ActionDelegate {

    @CheckForNull
    private MapEntity userEntity;

    @CheckForNull
    private MapEntity taskEntity;

    @Override
    public void complete(@Nonnull MapEntity userEntity, @Nonnull MapEntity taskEntity) {
        this.userEntity = userEntity;
        this.taskEntity = taskEntity;
    }

    @Override
    public void onUserAttack() {
        requireNonNull(taskEntity).isBeatenBy(requireNonNull(userEntity));
    }

    @Override
    public void onUserDefend() {
        requireNonNull(userEntity).defense();
    }
}
