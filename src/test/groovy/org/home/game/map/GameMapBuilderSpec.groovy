package org.home.game.map

import static org.home.game.map.GameMapBuilder.map
import static org.home.game.map.entities.MapEntityFactory.character
import static org.home.game.map.entities.MapEntityFactory.road
import static org.home.game.map.entities.MapEntityFactory.tree
import static org.home.game.map.entities.MapEntityFactory.userCharacter
import static org.home.game.map.entities.character.Race.HUMAN
import static org.home.game.map.entities.character.Race.ORC
import static org.home.game.map.entities.character.Sex.MALE

import org.home.game.map.entities.MapEntity
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class GameMapBuilderSpec extends Specification {

    void 'IllegalStateException should be thrown when empty map is creating'() {
        when:
            map().create()
        then:
            IllegalStateException exception = thrown(IllegalStateException)
            exception.message == 'It is impossible to create empty map'
    }

    void 'IllegalStateException should be thrown when empty line is creating'() {
        when:
            map().line()
        then:
            IllegalStateException exception = thrown(IllegalStateException)
            exception.message == 'It is impossible to create empty line of map'
    }

    void 'IllegalStateException should be thrown when different lines have different size'() {
        when:
            map().line(road(), road())
                 .line(secondLine as MapEntity[])
        then:
            IllegalStateException exception = thrown(IllegalStateException)
            exception.message == 'It is impossible to create map lines with different size'
        where:
            secondLine << [
                    [road()],
                    [road(), road(), road()],
            ]
    }

    void 'new map should be created'() {
        when:
            GameMap map = map()
                    .line(road(), tree(), road())
                    .line(road(), userCharacter('User', HUMAN, MALE), road())
                    .line(road(), character(ORC.toString(), ORC, MALE), road())
                    .create()
        then:
            map != null
        and:
            map instanceof MainGameMap
    }
}
