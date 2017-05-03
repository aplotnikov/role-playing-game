package org.home.game.common.utils.console

import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream

import org.junit.Rule
import org.junit.contrib.java.lang.system.TextFromStandardInputStream
import spock.lang.Specification
import spock.lang.Subject

class ConsoleIntegerReaderSpec extends Specification {

    @Rule
    TextFromStandardInputStream systemInMock = emptyStandardInputStream()

    @Subject
    ConsoleIntegerReader reader = new ConsoleIntegerReader()

    void 'reader should return valid input data'() {
        given:
            systemInMock.provideLines('', ' ', 'text', '0', '9', '1')
        and:
            Runnable onFail = Mock()
        when:
            int result = reader.readUntil({ line -> Integer.valueOf(line) == 1 }, onFail)
        then:
            result == 1
        and:
            5 * onFail.run()
    }
}
