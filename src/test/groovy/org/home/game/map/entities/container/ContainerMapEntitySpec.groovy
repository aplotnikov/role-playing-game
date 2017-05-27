package org.home.game.map.entities.container

import static org.home.game.map.entities.MapEntityType.ROAD

import nl.jqno.equalsverifier.EqualsVerifier
import org.home.game.map.entities.MapEntity
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Subject

class ContainerMapEntitySpec extends Specification {

    @Shared
    String containerName = 'Road'

    @Subject
    ContainerMapEntity container = new ContainerMapEntity(containerName, ROAD)

    void 'constructor argument should be set into fields and container has default behaviour'() {
        expect:
            with(container) {
                name == containerName
                type == ROAD
                !innerEntity.isPresent()
                canContainAnotherEntity()
                !containAnotherEntity()
            }
    }

    void 'container should take another element and throw away it'() {
        when:
            container.take(Stub(MapEntity))
        then:
            with(container) {
                container.containAnotherEntity()
                innerEntity.isPresent()
            }
        when:
            container.clear()
        then:
            with(container) {
                !containAnotherEntity()
                !innerEntity.isPresent()
            }
    }

    void 'equals and hashcode contract should be followed'() {
        expect:
            EqualsVerifier.forClass(ContainerMapEntity).usingGetClass().withIgnoredFields('entity').verify()
    }
}
