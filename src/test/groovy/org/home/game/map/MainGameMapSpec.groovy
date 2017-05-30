package org.home.game.map

import static org.home.game.map.GameMapBuilder.map
import static org.home.game.map.entities.MapEntityFactory.bear
import static org.home.game.map.entities.MapEntityFactory.character
import static org.home.game.map.entities.MapEntityFactory.road
import static org.home.game.map.entities.MapEntityFactory.tree
import static org.home.game.map.entities.MapEntityFactory.userCharacter
import static org.home.game.map.entities.MapEntityFactory.wolf
import static org.home.game.map.entities.character.Race.HUMAN
import static org.home.game.map.entities.character.Sex.MALE

import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class MainGameMapSpec extends Specification {

    void 'map should contain user character'() {
        expect:
            gameMap.containsUserCharacter()
        where:
            gameMap << [
                    map().line(road(), userCharacter('Andrii', HUMAN, MALE), tree()).create(),
                    map().line(road(), road(userCharacter('Andrii', HUMAN, MALE)), tree()).create()
            ]
    }

    void 'map should not contain user character'() {
        given:
            GameMap map = map().line(road(), character('Andrii', HUMAN, MALE), tree()).create()
        expect:
            !map.containsUserCharacter()
    }

    void 'map should contain tasks'() {
        expect:
            gameMap.containsTasks()
        where:
            gameMap << [
                    map().line(wolf()).create(),
                    map().line(bear()).create(),
                    map().line(character('Andrii Plotnikov', HUMAN, MALE)).create(),
                    map().line(road(bear())).create()
            ]
    }

    void 'map should not contain tasks'() {
        given:
            GameMap gameMap = map().line(road(), userCharacter('Andrii', HUMAN, MALE), tree()).create()
        expect:
            !gameMap.containsTasks()
    }
}
