package org.home.game.map.objects.animals

import static org.home.game.map.objects.animals.Wolf.wolf

import spock.lang.Specification
import spock.lang.Subject

class WolfSpec extends Specification {
    @Subject
    Wolf wolf = wolf()

    void 'default parameters should be initialized'() {
        expect:
            with(wolf) {
                name == 'Wolf'
                !isUser()
            }
    }
}
