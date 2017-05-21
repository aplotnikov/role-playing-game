package org.home.game.map.objects.animals

import static org.home.game.map.objects.animals.Bear.bear

import spock.lang.Specification
import spock.lang.Subject

class BearSpec extends Specification {

    @Subject
    Bear bear = bear()

    void 'default parameters should be initialized'() {
        expect:
            with(bear) {
                name == 'Bear'
                !isUser()
            }
    }
}
