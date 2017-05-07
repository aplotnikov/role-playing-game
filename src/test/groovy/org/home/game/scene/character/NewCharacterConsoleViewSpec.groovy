package org.home.game.scene.character

import static org.home.game.domain.Race.HUMAN
import static org.home.game.domain.Sex.MALE
import static org.home.game.scene.character.NewCharacterView.ActionDelegate
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream

import org.junit.Rule
import org.junit.contrib.java.lang.system.SystemOutRule
import org.junit.contrib.java.lang.system.TextFromStandardInputStream
import spock.lang.Specification
import spock.lang.Subject

class NewCharacterConsoleViewSpec extends Specification {
    @Rule
    SystemOutRule systemOutRule = new SystemOutRule().enableLog().muteForSuccessfulTests()

    @Rule
    TextFromStandardInputStream systemInMock = emptyStandardInputStream()

    ActionDelegate delegate = Mock()

    @Subject
    NewCharacterConsoleConsoleView view = new NewCharacterConsoleConsoleView()

    void setup() {
        view.setDelegate(delegate)
    }

    void 'new character menu should be drawn and all parameters should be filled in'() {
        given:
            String expectedResult = """\
                                      |New Character Menu
                                      |Character name:
                                      |Choose Race:
                                      |1. Human
                                      |2. Orc
                                      |3. Elf
                                      |4. Gnome
                                      |5. Troll
                                      |Put operation's number which you want to do: 
                                      |Choose Sex:
                                      |1. Male
                                      |2. Female
                                      |Put operation's number which you want to do: 
                                      |""".stripMargin()
        and:
            String characterName = 'character name'
        and:
            systemInMock.provideLines(
                    characterName,
                    '1', // race
                    '1' // sex
            )
        when:
            view.draw()
        then:
            systemOutRule.getLog() == expectedResult
        and:
            1 * delegate.onChosen(characterName)
            1 * delegate.onChosen(HUMAN)
            1 * delegate.onChosen(MALE)
            1 * delegate.onCompleted()
        and:
            0 * _
    }
}
