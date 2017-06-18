package org.home.game

import static java.lang.System.lineSeparator
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream

import org.junit.Rule
import org.junit.contrib.java.lang.system.SystemOutRule
import org.junit.contrib.java.lang.system.TextFromStandardInputStream
import spock.lang.Specification

abstract class ConsoleIntegrationSpec extends Specification {

    @Rule
    SystemOutRule systemOutRule = new SystemOutRule().enableLog().muteForSuccessfulTests()

    @Rule
    TextFromStandardInputStream systemInMock = emptyStandardInputStream()

    protected void assertOutput(String expectedOutput) {
        assert systemOutRule.log == expectedOutput
    }

    protected void assertOutputContains(String output) {
        assert systemOutRule.log.contains(output)
    }

    protected void userInput(String... input) {
        systemInMock.provideLines input
    }

    protected static String eraseOutput() {
        (1..50).collect { lineSeparator() }.join('')
    }
}
