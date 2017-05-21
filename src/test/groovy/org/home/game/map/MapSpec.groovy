package org.home.game.map

import static org.home.game.map.Map.map
import static org.home.game.map.objects.character.GameCharacter.character
import static org.home.game.map.objects.character.GameCharacter.userCharacter
import static org.home.game.map.objects.character.Race.HUMAN
import static org.home.game.map.objects.character.Race.ORC
import static org.home.game.map.objects.character.Sex.MALE
import static org.home.game.map.objects.container.Road.road
import static org.home.game.map.objects.immovable.Tree.tree

import org.home.game.map.objects.MapObject
import org.junit.Rule
import org.junit.contrib.java.lang.system.SystemOutRule
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

@Unroll
class MapSpec extends Specification {

    @Rule
    SystemOutRule systemOutRule = new SystemOutRule().enableLog().muteForSuccessfulTests()

    @Subject
    Map map = map()
            .line(road(), tree(), road())
            .line(road(), userCharacter('User', HUMAN, MALE), road())
            .line(road(), character(ORC.toString(), ORC, MALE), road())
            .create()

    void 'map should be drawn'() {
        given:
            String expectedOutput = """MAP
                                       |-----
                                       || T |
                                       || U |
                                       || O |
                                       |-----
                                       |""".stripMargin()
        when:
            map.draw()
        then:
            systemOutRule.getLog() == expectedOutput
    }

    void 'map should not contain tasks'() {
        expect:
            !map.containsTasks()
    }

    void 'map should contain user character'() {
        expect:
            map.containsUserCharacter()
    }

    void 'IllegalStateException should be thrown when empty map is creating'() {
        when:
            map().create()
        then:
            IllegalStateException exception = thrown(IllegalStateException)
            exception.message == 'It is impossible to create empty map'
    }

    void 'IllegalStateException should be thrown when empty line is creating'() {
        when:
            map().line()
        then:
            IllegalStateException exception = thrown(IllegalStateException)
            exception.message == 'It is impossible to create empty line of map'
    }

    void 'IllegalStateException should be thrown when different lines have different size'() {
        when:
            map().line(road(), road())
                 .line(secondLine as MapObject[])
        then:
            IllegalStateException exception = thrown(IllegalStateException)
            exception.message == 'It is impossible to create map lines with different size'
        where:
            secondLine << [
                    [road()],
                    [road(), road(), road()],
            ]
    }
}
