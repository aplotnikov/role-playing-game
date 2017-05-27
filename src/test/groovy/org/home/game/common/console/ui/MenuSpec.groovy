package org.home.game.common.console.ui

import static org.home.game.common.console.ui.MenuSpec.Item.ITEM_1

import org.home.game.ConsoleIntegrationSpec
import spock.lang.Shared
import spock.lang.Subject

class MenuSpec extends ConsoleIntegrationSpec {

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
        when:
            menu.draw()
        then:
            assertOutput """\
title
1. ITEM_1
2. ITEM_2
"""
    }

    void 'menu should be redrawn'() {
        when:
            menu.redraw()
        then:
            assertOutput """\
${eraseOutput()}\
title
1. ITEM_1
2. ITEM_2
"""
    }

    void 'menu should return correct input data'() {
        given:
            userInput '', ' ', 'incorrect data', '0', '3', '1'
        expect:
            menu.chooseItem() == ITEM_1
        and:
            assertOutput """\
Put operation's number which you want to do: 
${(1..5).collect { eraseOutput() + menuOutput() }.join('')}"""
    }

    private static String menuOutput() {
        """\
title
1. ITEM_1
2. ITEM_2
Operation number is incorrect. Please, type correct one.
Put operation's number which you want to do: 
"""
    }
}
