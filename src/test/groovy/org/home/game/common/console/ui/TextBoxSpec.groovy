package org.home.game.common.console.ui

import static java.lang.System.lineSeparator

import org.home.game.ConsoleIntegrationSpec
import spock.lang.Shared
import spock.lang.Subject

class TextBoxSpec extends ConsoleIntegrationSpec {

    @Shared
    String title = 'my text box'

    @Subject
    TextBox component = new TextBox(title)

    void 'component should print title'() {
        when:
            component.draw()
        then:
            assertOutput title + lineSeparator()
    }

    void 'component should be redrawn'() {
        when:
            component.redraw()
        then:
            assertOutput eraseOutput() + title + lineSeparator()
    }

    void 'component should return input text'() {
        given:
            String inputText = 'my text'
        and:
            userInput inputText
        expect:
            component.getValue() == inputText
    }
}
