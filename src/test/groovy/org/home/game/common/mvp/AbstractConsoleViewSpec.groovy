package org.home.game.common.mvp

import static java.lang.System.lineSeparator

import org.home.game.common.mvp.console.AbstractConsoleView
import org.junit.Rule
import org.junit.contrib.java.lang.system.SystemOutRule
import spock.lang.Specification
import spock.lang.Subject

class AbstractConsoleViewSpec extends Specification {

    private interface SomeActionDelegate {}

    @Rule
    SystemOutRule systemOutRule = new SystemOutRule().enableLog().muteForSuccessfulTests()

    @Subject
    AbstractConsoleView<SomeActionDelegate> view = new AbstractConsoleView<SomeActionDelegate>() {
        @Override
        void draw() {}
    }

    void 'delegate should be set'() {
        given:
            SomeActionDelegate delegate = Stub(SomeActionDelegate)
        when:
            view.setDelegate(delegate)
        then:
            old view.delegate == null
        and:
            view.delegate == delegate
    }

    void 'erase method should print 50 empty lines'() {
        given:
            String expectedOutput = (1..50).collect { lineSeparator() }.join('')
        when:
            view.erase()
        then:
            systemOutRule.getLog() == expectedOutput
    }
}
