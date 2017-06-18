package org.home.game.play

import org.home.game.map.GameMap
import org.home.game.map.factory.MapFactory
import spock.lang.Specification
import spock.lang.Subject

class GameFactoryImplSpec extends Specification {

    GameMap gameMap = Stub()

    MapFactory mapFactory = Stub() {
        create() >> gameMap
    }

    GameView gameView = Stub()

    @Subject
    GameFactoryImpl factory = new GameFactoryImpl(mapFactory, gameView)

    void 'new game should be created'() {
        when:
            Game firstGame = factory.create()
        then:
            firstGame instanceof GameImpl
            with(firstGame as GameImpl) {
                map == gameMap
                view == gameView
            }
        when:
            Game secondGame = factory.create()
        then:
            secondGame != null
            secondGame != firstGame
    }
}
