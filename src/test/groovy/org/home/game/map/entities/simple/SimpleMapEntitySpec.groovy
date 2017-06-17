package org.home.game.map.entities.simple

import static org.home.game.map.entities.MapEntityType.STONE

import nl.jqno.equalsverifier.EqualsVerifier
import org.home.game.map.entities.MapEntity
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Subject

import java.util.function.Predicate

class SimpleMapEntitySpec extends Specification {

    @Shared
    Predicate<MapEntity> foreverTrueCondition = { true }

    @Shared
    Predicate<MapEntity> foreverFalseCondition = { false }

    @Shared
    String entityName = 'Stone'

    @Subject
    SimpleMapEntity entity = new SimpleMapEntity(entityName, STONE, 10)

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
            entity.take(Stub(MapEntity))
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
            MapEntity anotherEntity = Stub() {
                getAttackPower() >> 10
            }
        when:
            entity.isBeatenBy(anotherEntity)
        then:
            entity.health == 90
    }

    void 'entity should be not beaten when entity is in defended mode'() {
        given:
            MapEntity anotherEntity = Stub() {
                getAttackPower() >> 10
            }
        and:
            entity.defense()
        when:
            entity.isBeatenBy(anotherEntity)
        then:
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
            EqualsVerifier.forClass(SimpleMapEntity)
                          .usingGetClass()
                          .withIgnoredFields('health', 'defended')
                          .verify()
    }
}
