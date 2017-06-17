package org.home.game.map.entities.container

import static org.home.game.map.entities.MapEntityFactory.character
import static org.home.game.map.entities.MapEntityFactory.road
import static org.home.game.map.entities.MapEntityFactory.stone
import static org.home.game.map.entities.MapEntityFactory.userCharacter
import static org.home.game.map.entities.MapEntityType.ROAD
import static org.home.game.map.entities.character.Race.HUMAN
import static org.home.game.map.entities.character.Sex.MALE

import nl.jqno.equalsverifier.EqualsVerifier
import org.home.game.map.entities.MapEntity
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

import java.util.function.Predicate

@Unroll
class ContainerMapEntitySpec extends Specification {

    @Shared
    Predicate<MapEntity> foreverTrueCondition = { true }

    @Shared
    String containerName = 'Road'

    @Shared
    MapEntity userCharacter = userCharacter('Andrii', HUMAN, MALE)

    @Shared
    MapEntity character = character('Andrii', HUMAN, MALE)

    @Subject
    ContainerMapEntity container = new ContainerMapEntity(containerName, ROAD, 0)

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
            container.take(Stub(MapEntity))
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
            Predicate<MapEntity> taskDetectionCondition = { it != container }
        and:
            MapEntity innerEntity = Stub() {
                isUser() >> false
                containTasks(taskDetectionCondition) >> true
            }
        and:
            container.take(innerEntity)
        expect:
            container.containTasks(taskDetectionCondition)
    }

    void 'equals and hashcode contract should be followed'() {
        expect:
            EqualsVerifier.forClass(ContainerMapEntity)
                          .usingGetClass()
                          .withIgnoredFields('innerEntity', 'health', 'defended')
                          .verify()
    }
}
