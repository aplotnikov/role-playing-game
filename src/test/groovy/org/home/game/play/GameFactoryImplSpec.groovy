package org.home.game.play

import org.home.game.map.GameMap
import org.home.game.map.factory.MapFactory
import spock.lang.Specification
import spock.lang.Subject

class GameFactoryImplSpec extends Specification {

    GameMap gameMap = Stub()

    MapFactory mapFactory = Stub()

    GameView gameView = Stub()

    @Subject
    GameFactoryImpl factory = new GameFactoryImpl(mapFactory, gameView)

    void 'new game should be created'() {
        given:
            mapFactory.create() >> gameMap
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

    void 'game should be restored'() {
        given:
            mapFactory.restore() >> gameMap
        when:
            Game firstGame = factory.resume()
        then:
            firstGame instanceof GameImpl
            with(firstGame as GameImpl) {
                map == gameMap
                view == gameView
            }
        when:
            Game secondGame = factory.resume()
        then:
            secondGame != null
            secondGame != firstGame
    }
}
