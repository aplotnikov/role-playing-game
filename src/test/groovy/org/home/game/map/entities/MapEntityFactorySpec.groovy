package org.home.game.map.entities

import static org.home.game.map.entities.MapEntityType.BEAR
import static org.home.game.map.entities.MapEntityType.CHARACTER
import static org.home.game.map.entities.MapEntityType.ROAD
import static org.home.game.map.entities.MapEntityType.STONE
import static org.home.game.map.entities.MapEntityType.TREE
import static org.home.game.map.entities.MapEntityType.WOLF
import static org.home.game.map.entities.character.Race.HUMAN
import static org.home.game.map.entities.character.Sex.MALE

import org.home.game.map.entities.character.GameCharacter
import org.home.game.map.entities.simple.SimpleMapEntity
import spock.lang.Specification

class MapEntityFactorySpec extends Specification {

    void 'road should be created without inner entity'() {
        when:
            MapEntity road = MapEntityFactory.road()
        then:
            road instanceof SimpleMapEntity
        and:
            with(road as SimpleMapEntity) {
                name == 'Road'
                type == ROAD
                health == 100
                attackPower == 0
                !containAnotherEntity()
            }
    }

    void 'road should be created with inner entity'() {
        given:
            MapEntity entity = Stub(MapEntity)
        when:
            MapEntity road = MapEntityFactory.road(entity)
        then:
            road instanceof SimpleMapEntity
        and:
            with(road as SimpleMapEntity) {
                name == 'Road'
                type == ROAD
                health == 100
                attackPower == 0
                containAnotherEntity()
            }
    }

    void 'wolf should be created'() {
        when:
            MapEntity wolf = MapEntityFactory.wolf()
        then:
            wolf instanceof SimpleMapEntity
        and:
            with(wolf as SimpleMapEntity) {
                name == 'Wolf'
                type == WOLF
                health == 100
                attackPower == 10
                !isDefended
                !containAnotherEntity()
            }
    }

    void 'bear should be created'() {
        when:
            MapEntity bear = MapEntityFactory.bear()
        then:
            bear instanceof SimpleMapEntity
        and:
            with(bear as SimpleMapEntity) {
                name == 'Bear'
                type == BEAR
                health == 100
                attackPower == 20
                !isDefended
                !containAnotherEntity()
            }
    }

    void 'tree should be created'() {
        when:
            MapEntity tree = MapEntityFactory.tree()
        then:
            tree instanceof SimpleMapEntity
        and:
            with(tree as SimpleMapEntity) {
                name == 'Tree'
                type == TREE
                health == 100
                attackPower == 0
                !isDefended
                !containAnotherEntity()
            }
    }

    void 'stone should be created'() {
        when:
            MapEntity stone = MapEntityFactory.stone()
        then:
            stone instanceof SimpleMapEntity
        and:
            with(stone as SimpleMapEntity) {
                name == 'Stone'
                type == STONE
                health == 100
                attackPower == 0
                !isDefended
                !containAnotherEntity()
            }
    }

    void 'user character should be created'() {
        when:
            MapEntity character = MapEntityFactory.userCharacter('Andrii', HUMAN, MALE)
        then:
            character instanceof GameCharacter
        and:
            with(character as GameCharacter) {
                name == 'Andrii'
                type == CHARACTER
                isUser()
                race == HUMAN
                sex == MALE
                health == 100
                attackPower == 40
                !containAnotherEntity()
            }
    }

    void 'character should be created'() {
        when:
            MapEntity character = MapEntityFactory.character('Andrii', HUMAN, MALE)
        then:
            character instanceof GameCharacter
        and:
            with(character as GameCharacter) {
                name == 'Andrii'
                type == CHARACTER
                !isUser()
                race == HUMAN
                sex == MALE
                health == 100
                attackPower == 30
                !containAnotherEntity()
            }
    }
}
