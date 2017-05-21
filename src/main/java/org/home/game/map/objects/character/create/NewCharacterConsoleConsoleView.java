package org.home.game.map.objects.character.create;

import lombok.experimental.FieldDefaults;
import org.home.game.map.objects.character.Race;
import org.home.game.map.objects.character.Sex;
import org.home.game.common.mvp.AbstractConsoleView;
import org.home.game.common.ui.Menu;
import org.home.game.common.ui.TextBox;

import static lombok.AccessLevel.PRIVATE;

@FieldDefaults(level = PRIVATE, makeFinal = true)
public class NewCharacterConsoleConsoleView extends AbstractConsoleView<NewCharacterView.ActionDelegate> implements NewCharacterView {

    TextBox characterName = new TextBox("Character name: ");

    Menu<Race> raceMenu = new Menu<>("Choose Race:", Race.values());

    Menu<Sex> sexMenu = new Menu<>("Choose Sex:", Sex.values());

    @Override
    public void draw() {
        System.out.println("New Character Menu");

        characterName.draw();
        delegate.onChosen(characterName.getValue());

        raceMenu.draw();
        delegate.onChosen(raceMenu.chooseItem());

        sexMenu.draw();
        delegate.onChosen(sexMenu.chooseItem());

        delegate.onCompleted();
    }
}
