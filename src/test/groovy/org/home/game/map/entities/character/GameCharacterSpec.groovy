package org.home.game.map.entities.character

import static org.home.game.map.entities.MapEntityType.CHARACTER
import static org.home.game.map.entities.character.Race.HUMAN
import static org.home.game.map.entities.character.Sex.MALE

import nl.jqno.equalsverifier.EqualsVerifier
import org.home.game.map.entities.MapEntity
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Subject

import java.util.function.Predicate

class GameCharacterSpec extends Specification {

    @Shared
    Predicate<MapEntity> foreverTrueCondition = { true }

    @Shared
    String characterName = 'Andrii'

    @Subject
    GameCharacter character = new GameCharacter(characterName, true, HUMAN, MALE)

    void 'constructor argument should be set into fields and character has default behaviour'() {
        expect:
            with(character) {
                name == characterName
                isUser()
                race == HUMAN
                sex == MALE
                type == CHARACTER
                !innerEntity.isPresent()
                !canContainAnotherEntity()
                !containAnotherEntity()
                !containUserCharacter()
                !containTasks(foreverTrueCondition)
            }
    }

    void 'UnsupportedOperationException should be thrown when take method is called'() {
        when:
            character.take(Stub(MapEntity))
        then:
            UnsupportedOperationException exception = thrown(UnsupportedOperationException)
            exception.message == 'This method is not supported.'
    }

    void 'UnsupportedOperationException should be thrown when clear method is called'() {
        when:
            character.clear()
        then:
            UnsupportedOperationException exception = thrown(UnsupportedOperationException)
            exception.message == 'This method is not supported.'
    }

    void 'equals and hashcode contract should be followed'() {
        expect:
            EqualsVerifier.forClass(GameCharacter).usingGetClass().verify()
    }
}
