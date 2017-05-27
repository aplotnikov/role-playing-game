package org.home.game.menu

import static org.home.game.menu.MenuView.ActionDelegate

import org.home.game.ConsoleIntegrationSpec
import spock.lang.Subject

class MenuConsoleViewSpec extends ConsoleIntegrationSpec {

    ActionDelegate delegate = Mock()

    @Subject
    MenuConsoleConsoleView view = new MenuConsoleConsoleView(delegate: delegate)

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
            assertOutput """\
Main menu
1. Start new game
2. Resume previous game
Put operation's number which you want to do: 
"""
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
            assertOutput """\
Main menu
1. Start new game
2. Resume previous game
Put operation's number which you want to do: 
"""
    }
}
