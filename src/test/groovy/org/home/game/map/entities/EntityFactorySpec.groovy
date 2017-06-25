package org.home.game.map.entities

import static EntityType.BEAR
import static EntityType.CHARACTER
import static EntityType.ROAD
import static EntityType.STONE
import static EntityType.TREE
import static EntityType.WOLF
import static org.home.game.map.entities.character.Race.HUMAN
import static org.home.game.map.entities.character.Sex.MALE

import org.home.game.map.entities.character.GameCharacter
import spock.lang.Specification

class EntityFactorySpec extends Specification {

    void 'road should be created without inner entity'() {
        when:
            Entity road = EntityFactory.road()
        then:
            with(road as SimpleEntity) {
                name == 'Road'
                type == ROAD
                health == 100
                attackPower == 0
                !defended
                !containAnotherEntity()
            }
    }

    void 'road should be created with inner entity'() {
        given:
            Entity entity = Stub(Entity)
        when:
            Entity road = EntityFactory.road(entity)
        then:
            with(road as SimpleEntity) {
                name == 'Road'
                type == ROAD
                health == 100
                attackPower == 0
                !defended
                containAnotherEntity()
            }
    }

    void 'wolf should be created'() {
        when:
            Entity wolf = EntityFactory.wolf()
        then:
            with(wolf as SimpleEntity) {
                name == 'Wolf'
                type == WOLF
                health == 100
                attackPower == 10
                !defended
                !containAnotherEntity()
            }
    }

    void 'bear should be created'() {
        when:
            Entity bear = EntityFactory.bear()
        then:
            with(bear as SimpleEntity) {
                name == 'Bear'
                type == BEAR
                health == 100
                attackPower == 20
                !defended
                !containAnotherEntity()
            }
    }

    void 'tree should be created'() {
        when:
            Entity tree = EntityFactory.tree()
        then:
            with(tree as SimpleEntity) {
                name == 'Tree'
                type == TREE
                health == 100
                attackPower == 0
                !defended
                !containAnotherEntity()
            }
    }

    void 'stone should be created'() {
        when:
            Entity stone = EntityFactory.stone()
        then:
            with(stone as SimpleEntity) {
                name == 'Stone'
                type == STONE
                health == 100
                attackPower == 0
                !defended
                !containAnotherEntity()
            }
    }

    void 'user character should be created'() {
        when:
            Entity character = EntityFactory.userCharacter('Andrii', HUMAN, MALE)
        then:
            with(character as GameCharacter) {
                name == 'Andrii'
                type == CHARACTER
                isUser()
                race == HUMAN
                sex == MALE
                health == 100
                attackPower == 50
                !defended
                !containAnotherEntity()
            }
    }

    void 'character should be created'() {
        when:
            Entity character = EntityFactory.character('Andrii', HUMAN, MALE)
        then:
            with(character as GameCharacter) {
                name == 'Andrii'
                type == CHARACTER
                !isUser()
                race == HUMAN
                sex == MALE
                health == 100
                attackPower == 30
                !defended
                !containAnotherEntity()
            }
    }
}
