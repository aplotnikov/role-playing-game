package org.home.game.play

import static java.lang.System.lineSeparator

import org.home.game.ConsoleIntegrationSpec
import spock.lang.Subject

class GameConsoleViewSpec extends ConsoleIntegrationSpec {

    @Subject
    GameConsoleView view = new GameConsoleView()

    void 'winner notification should be shown'() {
        when:
            view.showWinnerNotification()
        then:
            assertOutput "You won${lineSeparator()}"
    }

    void 'game over notification should be shown'() {
        when:
            view.showGameOverNotification()
        then:
            assertOutput "Game Over${lineSeparator()}"
    }
}
