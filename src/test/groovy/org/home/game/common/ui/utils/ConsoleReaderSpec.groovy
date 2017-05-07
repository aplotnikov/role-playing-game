package org.home.game.common.ui.utils

import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream

import org.junit.Rule
import org.junit.contrib.java.lang.system.TextFromStandardInputStream
import spock.lang.Specification
import spock.lang.Subject

class ConsoleReaderSpec extends Specification {

    @Rule
    TextFromStandardInputStream systemInMock = emptyStandardInputStream()

    @Subject
    ConsoleReader reader = new ConsoleReader()

    void 'reader should return valid integer value'() {
        given:
            systemInMock.provideLines('', ' ', 'text', '0', '9', '1')
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
            systemInMock.provideLines(value)
        expect:
            reader.readString() == value
    }
}
