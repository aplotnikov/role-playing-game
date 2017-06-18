package org.home.game.play

import org.home.game.map.GameMap
import spock.lang.Specification
import spock.lang.Subject

class GameImplSpec extends Specification {

    GameMap map = Mock()

    GameView view = Mock()

    @Subject
    GameImpl game = new GameImpl(map, view)

    void 'game should end when no user character on map'() {
        when:
            game.start()
        then:
            2 * view.draw(map)
        and:
            3 * map.containsUserCharacter() >>> [true, false, false]
            1 * map.containsTasks() >> true
            1 * map.goToNextIteration()
        and:
            1 * view.showGameOverNotification()
        and:
            0 * _
    }

    void 'game should end when no task to complete on map'() {
        when:
            game.start()
        then:
            2 * view.draw(map)
        and:
            3 * map.containsUserCharacter() >> true
            2 * map.containsTasks() >>> [true, false]
            1 * map.goToNextIteration()
        and:
            1 * view.showWinnerNotification()
        and:
            0 * _
    }
}
