package org.home.game.menu

import static MainMenuView.ActionDelegate

import org.home.game.ConsoleIntegrationSpec
import spock.lang.Subject

class MainMenuConsoleViewSpec extends ConsoleIntegrationSpec {

    ActionDelegate delegate = Mock()

    @Subject
    MainMainMenuConsoleConsoleView view = new MainMainMenuConsoleConsoleView(delegate: delegate)

    void 'main menu should be drawn and onStartChosen action should be called'() {
        given:
            userInput '1'
        when:
            view.draw()
        then:
            1 * delegate.onStartChosen()
        and:
            0 * _
        and:
            assertOutput mainMenuOutput()
    }

    void 'main menu should be drawn and onResumeChosen action should be called'() {
        given:
            userInput '2'
        when:
            view.draw()
        then:
            1 * delegate.onResumeChosen()
        and:
            0 * _
        and:
            assertOutput mainMenuOutput()
    }

    private static String mainMenuOutput() {
        """\
Main menu
1. Start new game
2. Resume previous game
Put operation's number which you want to do: 
"""
    }
}
