package org.home.game.play

import org.home.game.map.Map
import spock.lang.Specification
import spock.lang.Subject

class GameSpec extends Specification {

    Map map = Mock()

    @Subject
    Game game = new Game(map)

    void 'game should end when no user character on map'() {
        when:
            game.start()
        then:
            1 * map.draw()
            2 * map.containsUserCharacter() >>> [true, false]
            1 * map.containsTasks() >> true
            1 * map.goToNextIteration()
            1 * map.redraw()
        and:
            0 * _
    }

    void 'game should end when no task to complete on map'() {
        when:
            game.start()
        then:
            1 * map.draw()
            2 * map.containsUserCharacter() >> true
            2 * map.containsTasks() >>> [true, false]
            1 * map.goToNextIteration()
            1 * map.redraw()
        and:
            0 * _
    }
}
