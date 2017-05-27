package org.home.game.map.entities.simple

import static org.home.game.map.entities.MapEntityType.STONE

import nl.jqno.equalsverifier.EqualsVerifier
import org.home.game.map.entities.MapEntity
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Subject

class SimpleMapEntitySpec extends Specification {

    @Shared
    String entityName = 'Stone'

    @Subject
    SimpleMapEntity entity = new SimpleMapEntity(entityName, STONE)

    void 'constructor argument should be set into fields and entity has default behaviour'() {
        expect:
            with(entity) {
                name == entityName
                !isUser()
                type == STONE
                !innerEntity.isPresent()
                !canContainAnotherEntity()
                !containAnotherEntity()
            }
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

    void 'equals and hashcode contract should be followed'() {
        expect:
            EqualsVerifier.forClass(SimpleMapEntity).usingGetClass().verify()
    }
}
