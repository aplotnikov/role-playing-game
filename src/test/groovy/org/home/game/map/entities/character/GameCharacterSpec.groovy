package org.home.game.map.entities.character

import static org.home.game.map.entities.EntityType.CHARACTER
import static org.home.game.map.entities.character.Race.HUMAN
import static org.home.game.map.entities.character.Sex.MALE

import nl.jqno.equalsverifier.EqualsVerifier
import org.home.game.map.entities.Entity
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Subject

import java.util.function.Predicate

class GameCharacterSpec extends Specification {

    @Shared
    Predicate<Entity> foreverTrueCondition = { true }

    @Shared
    String characterName = 'Andrii'

    @Subject
    GameCharacter character = new GameCharacter(characterName, true, HUMAN, MALE, 10)

    void 'constructor argument should be set into fields and character has default behaviour'() {
        expect:
            with(character) {
                name == characterName
                isUser()
                race == HUMAN
                sex == MALE
                type == CHARACTER
                health == 100
                attackPower == 10
                !defended
                !innerEntity.isPresent()
                !canContainAnotherEntity()
                !containAnotherEntity()
                !containUserCharacter()
                !containTasks(foreverTrueCondition)
            }
    }

    void 'equals and hashcode contract should be followed'() {
        expect:
            EqualsVerifier.forClass(GameCharacter)
                          .usingGetClass()
                          .withIgnoredFields('health', 'defended')
                          .verify()
    }
}
