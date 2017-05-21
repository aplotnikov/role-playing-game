package org.home.game.map.objects.character

import static org.home.game.map.objects.character.GameCharacter.character
import static org.home.game.map.objects.character.GameCharacter.userCharacter
import static org.home.game.map.objects.character.Race.HUMAN
import static org.home.game.map.objects.character.Sex.MALE

import org.junit.Rule
import org.junit.contrib.java.lang.system.SystemOutRule
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Subject

class GameCharacterSpec extends Specification {

    @Rule
    SystemOutRule systemOutRule = new SystemOutRule().enableLog().muteForSuccessfulTests()

    @Shared
    String name = 'Andrii'

    @Subject
    GameCharacter character = character(name, HUMAN, MALE)

    void 'default parameters should be initialized'() {
        expect:
            with(character) {
                name == name
                !isUser()
                sex == MALE
                race == HUMAN
            }
    }

    void 'character should be drawn as default when it is not user'() {
        when:
            character.draw()
        then:
            systemOutRule.getLog() == 'A'
    }

    void 'character should be drawn as user when it is an user'() {
        given:
            GameCharacter character = userCharacter(name, HUMAN, MALE)
        when:
            character.draw()
        then:
            systemOutRule.getLog() == 'U'
    }

    void 'toString method should return information about character'() {
        expect:
            character.toString() == 'GameCharacter{name=Andrii, race=Human, sex=Male, isUserCharacter=false}'
    }
}
