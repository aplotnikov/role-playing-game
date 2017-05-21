package org.home.game.map.objects.container

import static org.home.game.map.objects.character.GameCharacter.character
import static org.home.game.map.objects.character.Race.HUMAN
import static org.home.game.map.objects.character.Sex.MALE
import static org.home.game.map.objects.container.Road.road

import org.home.game.map.objects.character.GameCharacter
import org.junit.Rule
import org.junit.contrib.java.lang.system.SystemOutRule
import spock.lang.Specification
import spock.lang.Subject

class RoadSpec extends Specification {

    @Rule
    SystemOutRule systemOutRule = new SystemOutRule().enableLog().muteForSuccessfulTests()

    GameCharacter character = character(HUMAN.toString(), HUMAN, MALE)

    @Subject
    Road road = road()

    void 'default parameters should be initialized'() {
        expect:
            with(road) {
                name == 'Road'
                !isUser()
                !containAnotherObject()
                canContainAnotherObject()
            }
    }

    void 'road should be drawn when no inner object'() {
        when:
            road.draw()
        then:
            systemOutRule.getLog() == ' '
    }

    void 'inner object should be drawn instead of road'() {
        given:
            road.take(character)
        when:
            road.draw()
        then:
            systemOutRule.getLog() == 'H'
    }

    void 'inner object should be added when take method is called'() {
        when:
            road.take(character)
        then:
            old !road.containAnotherObject()
            road.containAnotherObject()
    }

    void 'inner object should be removed when clear method is called'() {
        given:
            road.take(character)
        when:
            road.clear()
        then:
            old road.containAnotherObject()
            !road.containAnotherObject()
    }

    void 'toString method should return information about road with inner object'() {
        given:
            road.take(character)
        expect:
            road.toString() ==
            'Road{name=\'Road\', isUserCharacter=false, object=GameCharacter{name=Human, race=Human, sex=Male, isUserCharacter=false}}'
    }
}
