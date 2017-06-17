package org.home.game.map.entities

import static org.home.game.map.entities.EntityFactory.character
import static org.home.game.map.entities.EntityFactory.road
import static org.home.game.map.entities.EntityFactory.stone
import static org.home.game.map.entities.EntityFactory.userCharacter
import static org.home.game.map.entities.EntityType.ROAD
import static org.home.game.map.entities.character.Race.HUMAN
import static org.home.game.map.entities.character.Sex.MALE

import nl.jqno.equalsverifier.EqualsVerifier
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

import java.util.function.Predicate

@Unroll
class ContainerEntitySpec extends Specification {

    @Shared
    Predicate<Entity> foreverTrueCondition = { true }

    @Shared
    Predicate<Entity> foreverFalseCondition = { false }

    @Shared
    String containerName = 'Road'

    @Shared
    Entity userCharacter = userCharacter('Andrii', HUMAN, MALE)

    @Shared
    Entity character = character('Andrii', HUMAN, MALE)

    @Subject
    ContainerEntity container = new ContainerEntity(containerName, ROAD, 0)

    void 'constructor argument should be set into fields and container has default behaviour'() {
        expect:
            with(container) {
                name == containerName
                type == ROAD
                health == 100
                attackPower == 0
                !defended
                !innerEntity.isPresent()
                canContainAnotherEntity()
                !containAnotherEntity()
                !containUserCharacter()
                containTasks(foreverTrueCondition)
            }
    }

    void 'container should take another element and throw away it'() {
        when:
            container.take(Stub(Entity))
        then:
            container.containAnotherEntity()
        when:
            container.clear()
        then:
            !container.containAnotherEntity()
    }

    void 'container should contain user character when entity with user character is present - #entity'() {
        given:
            container.take entity
        expect:
            container.containUserCharacter()
        where:
            entity << [
                    userCharacter,
                    road(userCharacter),
                    road(road(userCharacter)),
                    road(road(road(userCharacter)))
            ]
    }

    void 'container should not contain user character when entity with user character is not present - #entity'() {
        given:
            container.take entity
        expect:
            !container.containUserCharacter()
        where:
            entity << [
                    stone(),
                    character,
                    road(character),
                    road(road(character)),
                    road(road(road(character)))
            ]
    }

    void 'container should contain tasks when condition is true'() {
        expect:
            container.containTasks(foreverTrueCondition)
    }

    void 'container should contain tasks when condition is false for container and inner entity contains tasks'() {
        given:
            Predicate<Entity> taskDetectionCondition = { it != container }
        and:
            Entity innerEntity = Stub() {
                isUser() >> false
                containTasks(taskDetectionCondition) >> true
            }
        and:
            container.take(innerEntity)
        expect:
            container.containTasks(taskDetectionCondition)
    }

    void 'inner entity should be found when it matches condition'() {
        given:
            container.take character
        expect:
            container.findEntity(foreverTrueCondition) == character
    }

    void 'inner entity of inner entity should be found when it matches condition'() {
        given:
            Predicate<Entity> condition = Stub() {
                test(_ as Entity) >>> [false, true]
            }
        and:
            container.take road(character)
        expect:
            container.findEntity(condition) == character
    }

    void 'IllegalStateException should be thrown when no entities match condition'() {
        given:
            container.take road(character)
        when:
            container.findEntity(foreverFalseCondition)
        then:
            IllegalStateException exception = thrown(IllegalStateException)
            exception.message == 'There is no entities with such condition'
    }

    void 'equals and hashcode contract should be followed'() {
        expect:
            EqualsVerifier.forClass(ContainerEntity)
                          .usingGetClass()
                          .withIgnoredFields('innerEntity', 'health', 'defended')
                          .verify()
    }
}
