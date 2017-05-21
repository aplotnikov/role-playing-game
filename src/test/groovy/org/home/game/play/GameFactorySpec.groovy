package org.home.game.play

import org.home.game.map.Map
import org.home.game.map.factory.MapFactory
import spock.lang.Specification
import spock.lang.Subject

class GameFactorySpec extends Specification {

    Map map = Stub()

    MapFactory mapFactory = Stub() {
        create() >> map
    }

    @Subject
    GameFactory factory = new GameFactory(mapFactory)

    void 'new game should be created'() {
        when:
            Game firstGame = factory.create()
        then:
            firstGame != null
            firstGame.map == map
        when:
            Game secondGame = factory.create()
        then:
            secondGame != null
            secondGame != firstGame
    }
}
