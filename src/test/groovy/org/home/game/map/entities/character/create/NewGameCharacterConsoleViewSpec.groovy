package org.home.game.map.entities.character.create

import static org.home.game.map.entities.character.Race.HUMAN
import static org.home.game.map.entities.character.Sex.MALE
import static org.home.game.map.entities.character.create.NewCharacterView.ActionDelegate

import org.home.game.ConsoleIntegrationSpec
import spock.lang.Subject

class NewGameCharacterConsoleViewSpec extends ConsoleIntegrationSpec {

    ActionDelegate delegate = Mock()

    @Subject
    NewCharacterConsoleConsoleView view = new NewCharacterConsoleConsoleView(delegate: delegate)

    void 'new character menu should be drawn and all parameters should be filled in'() {
        given:
            String characterName = 'character name'
        and:
            userInput characterName,
                      '1', // race
                      '1' // sex
        when:
            view.draw()
        then:
            assertOutput """\
New Character Menu
Character name:
Choose Race:
1. Human
2. Orc
3. Elf
4. Gnome
5. Troll
Put operation's number which you want to do:
Choose Sex:
1. Male
2. Female
Put operation's number which you want to do:
"""
        and:
            1 * delegate.onChosen(characterName)
            1 * delegate.onChosen(HUMAN)
            1 * delegate.onChosen(MALE)
            1 * delegate.onCompleted()
        and:
            0 * _
    }
}
