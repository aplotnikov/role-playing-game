package org.home.game.map.task.fight;

import org.home.game.common.console.ui.Menu;
import org.home.game.common.mvp.console.AbstractConsoleView;
import org.home.game.map.task.fight.FightView.ActionDelegate;

import javax.annotation.Nonnull;

public class FightConsoleView extends AbstractConsoleView<ActionDelegate> implements FightView {

    private final Menu<FightAction> menu = new Menu<>("Choose your action:", FightAction.values());

    @Override
    public void draw() {
        menu.draw();
        switch (menu.chooseItem()) {
            case BEAT:
                delegate.onUserAttack();
                break;
            case DEFENSE:
                delegate.onUserDefend();
                break;
            case DO_NOTHING:
                delegate.onDoNothing();
                break;
            default:
        }
    }

    private enum FightAction {
        BEAT("Beat the opponent"),
        DEFENSE("Defense yourself"),
        DO_NOTHING("Do nothing");

        private final String title;

        FightAction(@Nonnull String title) {
            this.title = title;
        }

        @Override
        public String toString() {
            return title;
        }
    }
}
