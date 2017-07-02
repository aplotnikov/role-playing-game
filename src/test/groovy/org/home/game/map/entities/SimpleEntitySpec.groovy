package org.home.game.map.entities

import static org.home.game.map.entities.EntityFactory.character
import static org.home.game.map.entities.EntityType.STONE
import static org.home.game.map.entities.character.Race.HUMAN
import static org.home.game.map.entities.character.Sex.FEMALE

import nl.jqno.equalsverifier.EqualsVerifier
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Subject

import java.util.function.Predicate

class SimpleEntitySpec extends Specification {

    @Shared
    Predicate<Entity> foreverTrueCondition = { true }

    @Shared
    Predicate<Entity> foreverFalseCondition = { false }

    @Shared
    String entityName = 'Stone'

    @Subject
    SimpleEntity entity = new SimpleEntity(entityName, STONE, 10)

    void 'constructor argument should be set into fields and entity has default behaviour'() {
        expect:
            with(entity) {
                name == entityName
                !isUser()
                type == STONE
                health == 100
                attackPower == 10
                isAlive()
                !defended
                !innerEntity.isPresent()
                !canContainAnotherEntity()
                !containAnotherEntity()
                !containUserCharacter()
                containTasks(foreverTrueCondition)
            }
    }

    void 'entity should be not able to contain another entities'() {
        expect:
            !entity.canContainAnotherEntity()
    }

    void 'entity should contain no another entities'() {
        expect:
            !entity.containAnotherEntity()
        and:
            entity.containAnotherEntity() == entity.innerEntity.present
    }

    void 'entity should contain no tasks'() {
        expect:
            entity.containTasks(foreverTrueCondition)
        and:
            !entity.containTasks(foreverFalseCondition)
    }

    void 'UnsupportedOperationException should be thrown when take method is called'() {
        when:
            entity.take(Stub(Entity))
        then:
            UnsupportedOperationException exception = thrown(UnsupportedOperationException)
            exception.message == 'This method is not supported.'
    }

    void 'UnsupportedOperationException should be thrown when clear method is called'() {
        when:
            entity.clear()
        then:
            UnsupportedOperationException exception = thrown(UnsupportedOperationException)
            exception.message == 'This method is not supported.'
    }

    void 'entity should be beaten by another entity'() {
        given:
            Entity anotherEntity = character('Some character', HUMAN, FEMALE, 10)
        when:
            int damage = entity.isBeatenBy(anotherEntity)
        then:
            damage == 10
        and:
            entity.health == 90
    }

    void 'entity health should be zero when attack of another entity is more than available health'() {
        given:
            Entity anotherEntity = character('Some character', HUMAN, FEMALE, 100)
        expect:
            entity.isBeatenBy(anotherEntity) == 100
        when:
            int damage = entity.isBeatenBy(anotherEntity)
        then:
            damage == 0
        and:
            old(entity.health) == entity.health
    }

    void 'entity should be not beaten when entity is in defended mode'() {
        given:
            Entity anotherEntity = character('Some character', HUMAN, FEMALE, 1)
        and:
            entity.defense()
        when:
            int damage = entity.isBeatenBy(anotherEntity)
        then:
            damage == 0
        and:
            entity.health == 100
    }

    void 'entity should be not in defended mode when it relaxes'() {
        given:
            entity.defense()
        when:
            entity.relax()
        then:
            old(entity.defended)
        and:
            !entity.defended
    }

    void 'equals and hashcode contract should be followed'() {
        expect:
            EqualsVerifier.forClass(SimpleEntity)
                          .usingGetClass()
                          .withIgnoredFields('health', 'defended')
                          .verify()
    }
}
