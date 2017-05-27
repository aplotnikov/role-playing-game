package org.home.game.play

import org.home.game.map.GameMap
import org.home.game.map.factory.MapFactory
import org.home.game.map.painter.MapPainter
import spock.lang.Specification
import spock.lang.Subject

class GameFactorySpec extends Specification {

    GameMap gameMap = Stub()

    MapFactory mapFactory = Stub() {
        create() >> gameMap
    }

    MapPainter mapPainter = Stub()

    @Subject
    GameFactory factory = new GameFactory(mapFactory, mapPainter)

    void 'new game should be created'() {
        when:
            Game firstGame = factory.create()
        then:
            with(firstGame) {
                map == gameMap
                painter == mapPainter
            }
        when:
            Game secondGame = factory.create()
        then:
            secondGame != null
            secondGame != firstGame
    }
}
