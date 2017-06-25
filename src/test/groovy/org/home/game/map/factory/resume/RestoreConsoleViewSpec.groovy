package org.home.game.map.factory.resume

import org.home.game.ConsoleIntegrationSpec
import spock.lang.Subject

class RestoreConsoleViewSpec extends ConsoleIntegrationSpec {
    @Subject
    RestoreConsoleView view = new RestoreConsoleView()

    void 'view should be drawn'() {
        when:
            view.draw()
        then:
            assertOutput appendLineSeparatorTo('Type file location: ')
    }

    void 'inputted value should be returned'() {
        given:
            String value = '/some/path/'
        and:
            userInput value
        expect:
            view.path == value
    }
}
