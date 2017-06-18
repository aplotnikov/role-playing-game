package org.home.game.map.task.fight;

import org.home.game.common.console.ui.Menu;
import org.home.game.common.mvp.console.AbstractConsoleView;
import org.home.game.map.entities.Entity;
import org.home.game.map.entities.character.GameCharacter;
import org.home.game.map.task.fight.FightView.ActionDelegate;

import javax.annotation.Nonnull;

import static java.lang.String.format;

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

    @Override
    public void drawUser(@Nonnull Entity user) {
        drawEntity(user);
    }

    @Override
    public void drawEnemy(@Nonnull Entity enemy) {
        drawEntity(enemy);
    }

    @Override
    public void drawAttack(@Nonnull Entity attacker, @Nonnull Entity defender, int damage) {
        System.out.println(format(
                "%s attacks %s. %s got a damage - %d",
                attacker.getName(), defender.getName(), defender.getName(), damage
        ));
    }

    private void drawEntity(@Nonnull Entity entity) {
        if (entity instanceof GameCharacter) {
            GameCharacter character = (GameCharacter) entity;
            System.out.println(format(
                    "Name: %s; Race: %s; Sex: %s; Health: %d; Attack power: %d",
                    character.getName(), character.getRace(), character.getSex(), character.getHealth(), character.getAttackPower()

            ));
        } else {
            System.out.println(format(
                    "Name: %s; Type: %s; Health: %d; Attack power: %d",
                    entity.getName(), entity.getType(), entity.getHealth(), entity.getAttackPower()
            ));
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
