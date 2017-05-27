package org.home.game.play

import org.home.game.map.GameMap
import org.home.game.map.painter.MapPainter
import spock.lang.Specification
import spock.lang.Subject

class GameSpec extends Specification {

    GameMap map = Mock()

    MapPainter painter = Mock()

    @Subject
    Game game = new Game(map, painter)

    void 'game should end when no user character on map'() {
        when:
            game.start()
        then:
            1 * painter.draw(map)
            2 * map.containsUserCharacter() >>> [true, false]
            1 * map.containsTasks() >> true
            1 * map.goToNextIteration()
            1 * painter.refresh()
        and:
            0 * _
    }

    void 'game should end when no task to complete on map'() {
        when:
            game.start()
        then:
            1 * painter.draw(map)
            2 * map.containsUserCharacter() >> true
            2 * map.containsTasks() >>> [true, false]
            1 * map.goToNextIteration()
            1 * painter.refresh()
        and:
            0 * _
    }
}
