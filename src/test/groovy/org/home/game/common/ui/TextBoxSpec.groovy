package org.home.game.common.ui

import static java.lang.System.lineSeparator
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream

import org.junit.Rule
import org.junit.contrib.java.lang.system.SystemOutRule
import org.junit.contrib.java.lang.system.TextFromStandardInputStream
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Subject

class TextBoxSpec extends Specification {

    @Rule
    SystemOutRule systemOutRule = new SystemOutRule().enableLog().muteForSuccessfulTests()

    @Rule
    TextFromStandardInputStream systemInMock = emptyStandardInputStream()

    @Shared
    String title = 'my text box'

    @Subject
    TextBox component = new TextBox(title)

    void 'component should print title'() {
        given:
            String expectOutput = title + lineSeparator()
        when:
            component.draw()
        then:
            systemOutRule.getLog() == expectOutput
    }

    void 'component should be redrawn'() {
        given:
            String expectOutput = (1..50).collect { lineSeparator() }.join('') + title + lineSeparator()
        when:
            component.redraw()
        then:
            systemOutRule.getLog() == expectOutput
    }

    void 'component should return input text'() {
        given:
            String inputText = 'my text'
        and:
            systemInMock.provideLines(inputText)
        expect:
            component.getValue() == inputText
    }
}
