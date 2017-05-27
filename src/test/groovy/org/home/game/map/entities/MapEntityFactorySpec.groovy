package org.home.game.map.entities

import static org.home.game.map.entities.MapEntityType.BEAR
import static org.home.game.map.entities.MapEntityType.CHARACTER
import static org.home.game.map.entities.MapEntityType.ROAD
import static org.home.game.map.entities.MapEntityType.STONE
import static org.home.game.map.entities.MapEntityType.TREE
import static org.home.game.map.entities.MapEntityType.WOLF
import static org.home.game.map.entities.character.Race.HUMAN
import static org.home.game.map.entities.character.Sex.MALE

import spock.lang.Specification

class MapEntityFactorySpec extends Specification {

    void 'road should be created without inner entity'() {
        when:
            MapEntity road = MapEntityFactory.road()
        then:
            with(road) {
                name == 'Road'
                type == ROAD
                !containAnotherEntity()
            }
    }

    void 'road should be created with inner entity'() {
        given:
            MapEntity entity = Stub(MapEntity)
        when:
            MapEntity road = MapEntityFactory.road(entity)
        then:
            with(road) {
                name == 'Road'
                type == ROAD
                containAnotherEntity()
            }
    }

    void 'wolf should be created'() {
        when:
            MapEntity road = MapEntityFactory.wolf()
        then:
            with(road) {
                name == 'Wolf'
                type == WOLF
                !containAnotherEntity()
            }
    }

    void 'bear should be created'() {
        when:
            MapEntity road = MapEntityFactory.bear()
        then:
            with(road) {
                name == 'Bear'
                type == BEAR
                !containAnotherEntity()
            }
    }

    void 'tree should be created'() {
        when:
            MapEntity road = MapEntityFactory.tree()
        then:
            with(road) {
                name == 'Tree'
                type == TREE
                !containAnotherEntity()
            }
    }

    void 'stone should be created'() {
        when:
            MapEntity road = MapEntityFactory.stone()
        then:
            with(road) {
                name == 'Stone'
                type == STONE
                !containAnotherEntity()
            }
    }

    void 'user character should be created'() {
        when:
            MapEntity road = MapEntityFactory.userCharacter('Andrii', HUMAN, MALE)
        then:
            with(road) {
                name == 'Andrii'
                type == CHARACTER
                isUser()
                race == HUMAN
                sex == MALE
                !containAnotherEntity()
            }
    }

    void 'character should be created'() {
        when:
            MapEntity road = MapEntityFactory.character('Andrii', HUMAN, MALE)
        then:
            with(road) {
                name == 'Andrii'
                type == CHARACTER
                !isUser()
                race == HUMAN
                sex == MALE
                !containAnotherEntity()
            }
    }
}
