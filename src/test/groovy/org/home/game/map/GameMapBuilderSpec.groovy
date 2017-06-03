package org.home.game.map

import static org.home.game.map.GameMapBuilder.map
import static org.home.game.map.entities.MapEntityFactory.character
import static org.home.game.map.entities.MapEntityFactory.road
import static org.home.game.map.entities.MapEntityFactory.tree
import static org.home.game.map.entities.MapEntityFactory.userCharacter
import static org.home.game.map.entities.MapEntityType.ROAD
import static org.home.game.map.entities.MapEntityType.TREE
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
        given:
            MapEntity userCharacter = userCharacter('User', HUMAN, MALE)
        and:
            MapEntity orc = character(ORC.toString(), ORC, MALE)
        when:
            List<List<MapEntity>> map = map()
                    .line(road(), tree(), road())
                    .line(road(), road(userCharacter), road())
                    .line(road(), road(orc), road())
                    .create()
        then:
            map != null
        and:
            map[0]*.type == [ROAD, TREE, ROAD]
            map[1]*.type == [ROAD, ROAD, ROAD]
            map[2]*.type == [ROAD, ROAD, ROAD]
        and:
            with(map[1][1].innerEntity) {
                present
                get() == userCharacter
            }
        and:
            with(map[2][1].innerEntity) {
                present
                get() == orc
            }
    }
}
