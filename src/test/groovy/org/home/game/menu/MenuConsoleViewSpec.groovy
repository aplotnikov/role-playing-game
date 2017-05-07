package org.home.game.menu

import static org.home.game.menu.MenuView.ActionDelegate
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream

import org.junit.Rule
import org.junit.contrib.java.lang.system.SystemOutRule
import org.junit.contrib.java.lang.system.TextFromStandardInputStream
import spock.lang.Specification
import spock.lang.Subject

class MenuConsoleViewSpec extends Specification {

    @Rule
    SystemOutRule systemOutRule = new SystemOutRule().enableLog().muteForSuccessfulTests()

    @Rule
    TextFromStandardInputStream systemInMock = emptyStandardInputStream()

    ActionDelegate delegate = Mock()

    @Subject
    MenuConsoleConsoleView view = new MenuConsoleConsoleView()

    void setup() {
        view.setDelegate(delegate)
    }

    void 'main menu should be drawn and onStartChosen action should be called'() {
        given:
            systemInMock.provideLines('1')
        and:
            String expectedOutput = """Main menu
                                      |1. Start new game
                                      |2. Resume previous game
                                      |Put operation's number which you want to do: 
                                      |""".stripMargin()
        when:
            view.draw()
        then:
            1 * delegate.onStartChosen()
        and:
            0 * _
        and:
            systemOutRule.getLog() == expectedOutput
    }

    void 'main menu should be drawn and onResumeChosen action should be called'() {
        given:
            systemInMock.provideLines('2')
        and:
            String expectedOutput = """Main menu
                                      |1. Start new game
                                      |2. Resume previous game
                                      |Put operation's number which you want to do: 
                                      |""".stripMargin()
        when:
            view.draw()
        then:
            1 * delegate.onResumeChosen()
        and:
            0 * _
        and:
            systemOutRule.getLog() == expectedOutput
    }
}
