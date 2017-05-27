package org.home.game.common.console.ui.utils

import org.home.game.ConsoleIntegrationSpec
import spock.lang.Subject

class ConsoleReaderSpec extends ConsoleIntegrationSpec {

    @Subject
    ConsoleReader reader = new ConsoleReader()

    void 'reader should return valid integer value'() {
        given:
            userInput '', ' ', 'text', '0', '9', '1'
        and:
            Runnable onFail = Mock()
        when:
            int result = reader.readIntegerUntil({ line -> Integer.valueOf(line) == 1 }, onFail)
        then:
            result == 1
        and:
            5 * onFail.run()
        and:
            0 * _
    }

    void 'reader should return any string'() {
        given:
            String value = 'some string'
        and:
            userInput value
        expect:
            reader.readString() == value
    }
}
