package org.home.game.common.console.ui

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
            assertOutput appendLineSeparatorTo(title)
    }

    void 'component should be redrawn'() {
        when:
            component.redraw()
        then:
            assertOutput eraseOutput() + appendLineSeparatorTo(title)
    }

    void 'component should return input text'() {
        given:
            String inputText = 'my text'
        and:
            userInput inputText
        expect:
            component.value == inputText
    }
}
