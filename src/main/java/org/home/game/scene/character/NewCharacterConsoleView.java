package org.home.game.scene.character;

import org.home.game.common.mvp.AbstractConsoleView;
import org.home.game.common.utils.console.ConsoleReader;
import org.home.game.domain.Race;
import org.home.game.domain.Sex;
import org.home.game.scene.character.NewCharacterView.ActionDelegate;

import javax.annotation.Nonnull;

public class NewCharacterConsoleView extends AbstractConsoleView<ActionDelegate> implements NewCharacterView {

    public NewCharacterConsoleView(@Nonnull ConsoleReader reader) {
        super(reader);
    }

    @Override
    public void draw() {
        System.out.println("New Character Menu");

        System.out.println("Character name:");
        delegate.onChosen(reader.readString());

        printRaceMenu(false);
        delegate.onChosen(readChosenMenuItem(Race.values(), () -> printRaceMenu(true)));

        printSexMenu(false);
        delegate.onChosen(readChosenMenuItem(Sex.values(), () -> printSexMenu(true)));

        delegate.onCompleted();
    }

    private void printSexMenu(boolean hasToPrintWarning) {
        printMenu("Choose Sex:", hasToPrintWarning, Sex.values());
    }

    private void printRaceMenu(boolean hasToPrintWarning) {
        printMenu("Choose Race:", hasToPrintWarning, Race.values());
    }
}
