package org.home.game.map.objects.immovable

import static org.home.game.map.objects.immovable.Stone.stone

import spock.lang.Specification
import spock.lang.Subject

class StoneSpec extends Specification {
    @Subject
    Stone stone = stone()

    void 'default parameters should be initialized'() {
        expect:
            with(stone) {
                name == 'Stone'
                !isUser()
            }
    }
}
