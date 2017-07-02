package org.home.game

import static java.lang.System.lineSeparator
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream

import org.junit.Rule
import org.junit.contrib.java.lang.system.SystemOutRule
import org.junit.contrib.java.lang.system.TextFromStandardInputStream
import spock.lang.Specification

class ConsoleIntegrationSpec extends Specification {

    @Rule
    SystemOutRule systemOutRule = new SystemOutRule().enableLog().muteForSuccessfulTests()

    @Rule
    TextFromStandardInputStream systemInMock = emptyStandardInputStream()

    protected void assertOutput(String expectedOutput) {
        assert systemOutRule.log == expectedOutput
    }

    protected void assertOutputEndWith(String suffix) {
        assert systemOutRule.log.endsWith(suffix)
    }

    protected void userInput(String... input) {
        systemInMock.provideLines input
    }

    protected static String eraseOutput() {
        (1..50).collect { lineSeparator() }.join('')
    }

    protected static String appendLineSeparatorTo(String text) {
        text + lineSeparator()
    }
}
