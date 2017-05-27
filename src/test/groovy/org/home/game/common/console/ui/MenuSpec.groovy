package org.home.game.common.console.ui

import static java.lang.System.lineSeparator
import static org.home.game.common.console.ui.MenuSpec.Item.ITEM_1
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream

import org.junit.Rule
import org.junit.contrib.java.lang.system.SystemOutRule
import org.junit.contrib.java.lang.system.TextFromStandardInputStream
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Subject

class MenuSpec extends Specification {

    @Rule
    SystemOutRule systemOutRule = new SystemOutRule().enableLog().muteForSuccessfulTests()

    @Rule
    TextFromStandardInputStream systemInMock = emptyStandardInputStream()

    enum Item {
        ITEM_1, ITEM_2
    }

    @Shared
    String title = 'title'

    @Subject
    Menu<Item> menu = new Menu<>(title, Item.values() as Item[])

    void 'IllegalArgumentException should be thrown when no items are configured'() {
        when:
            new Menu<Item>(title)
        then:
            IllegalArgumentException exception = thrown(IllegalArgumentException)
            exception.message == 'There are no configured menu items'
    }

    void 'menu should be printed'() {
        given:
            String expectedOutput = """title
                                      |1. ITEM_1
                                      |2. ITEM_2
                                      |""".stripMargin()
        when:
            menu.draw()
        then:
            systemOutRule.getLog() == expectedOutput
    }

    void 'menu should be redrawn'() {
        given:
            String expectedOutput = """${eraseMenuOutput()}\
                                      |title
                                      |1. ITEM_1
                                      |2. ITEM_2
                                      |""".stripMargin()
        when:
            menu.redraw()
        then:
            systemOutRule.getLog() == expectedOutput
    }

    void 'menu should return correct input data'() {
        given:
            String expectedOutput = """Put operation's number which you want to do: 
                                      |${(1..5).collect { eraseMenuOutput() + menuOutput() }.join('')}""".stripMargin()
        and:
            systemInMock.provideLines '', ' ', 'incorrect data', '0', '3', '1'
        expect:
            menu.chooseItem() == ITEM_1
        and:
            systemOutRule.getLog() == expectedOutput
    }

    private static String menuOutput() {
        """title
          |1. ITEM_1
          |2. ITEM_2
          |Operation number is incorrect. Please, type correct one.
          |Put operation's number which you want to do: 
          |""".stripMargin()
    }

    private static String eraseMenuOutput() {
        (1..50).collect { lineSeparator() }.join('')
    }
}
