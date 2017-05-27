package org.home.game.common.mvp

import org.home.game.ConsoleIntegrationSpec
import org.home.game.common.mvp.console.AbstractConsoleView
import spock.lang.Subject

class AbstractConsoleViewSpec extends ConsoleIntegrationSpec {

    private interface SomeActionDelegate {}

    @Subject
    AbstractConsoleView<SomeActionDelegate> view = new AbstractConsoleView<SomeActionDelegate>() {
        @Override
        void draw() {}
    }

    void 'delegate should be set'() {
        given:
            SomeActionDelegate delegate = Stub(SomeActionDelegate)
        when:
            view.delegate = delegate
        then:
            old view.delegate == null
        and:
            view.delegate == delegate
    }

    void 'erase method should print 50 empty lines'() {
        when:
            view.erase()
        then:
            assertOutput eraseOutput()
    }
}
