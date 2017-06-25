package org.home.game.map.task.fight

import static org.home.game.map.entities.EntityFactory.userCharacter
import static org.home.game.map.entities.EntityFactory.wolf
import static org.home.game.map.entities.character.Race.HUMAN
import static org.home.game.map.entities.character.Sex.MALE
import static org.home.game.map.task.fight.FightView.ActionDelegate

import org.home.game.ConsoleIntegrationSpec
import spock.lang.Subject

class FightConsoleViewSpec extends ConsoleIntegrationSpec {

    ActionDelegate actionDelegate = Mock()

    @Subject
    FightConsoleView view = new FightConsoleView(delegate: actionDelegate)

    void 'fight menu should be drawn and onUserAttack action should be called'() {
        given:
            userInput '1'
        when:
            view.draw()
        then:
            1 * actionDelegate.onUserAttack()
        and:
            0 * _
        and:
            assertOutput fightMenuOutput()
    }

    void 'fight menu should be drawn and onUserDefend action should be called'() {
        given:
            userInput '2'
        when:
            view.draw()
        then:
            1 * actionDelegate.onUserDefend()
        and:
            0 * _
        and:
            assertOutput fightMenuOutput()
    }

    void 'fight menu should be drawn and onDoNothing action should be called'() {
        given:
            userInput '3'
        when:
            view.draw()
        then:
            1 * actionDelegate.onDoNothing()
        and:
            0 * _
        and:
            assertOutput fightMenuOutput()
    }

    void 'user character should be drawn'() {
        when:
            view.drawUser userCharacter('Andrii', HUMAN, MALE)
        then:
            assertOutput appendLineSeparatorTo('Name: Andrii; Race: Human; Sex: Male; Health: 100; Attack power: 50')
    }

    void 'enemy should be drawn'() {
        when:
            view.drawUser wolf()
        then:
            assertOutput appendLineSeparatorTo('Name: Wolf; Type: WOLF; Health: 100; Attack power: 10')
    }

    void 'attack should be drawn'() {
        when:
            view.drawAttack(userCharacter('Andrii', HUMAN, MALE), wolf(), 10)
        then:
            assertOutput appendLineSeparatorTo('Andrii attacks Wolf. Wolf got a damage - 10')
    }

    private static String fightMenuOutput() {
        """\
Choose your action:
1. Beat the opponent
2. Defense yourself
3. Do nothing
Put operation's number which you want to do:
"""
    }
}
