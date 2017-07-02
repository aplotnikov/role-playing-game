package org.home.game.play

import static org.home.game.map.GameMapBuilder.map
import static org.home.game.map.entities.EntityFactory.character
import static org.home.game.map.entities.EntityFactory.road
import static org.home.game.map.entities.EntityFactory.stone
import static org.home.game.map.entities.EntityFactory.tree
import static org.home.game.map.entities.EntityFactory.userCharacter
import static org.home.game.map.entities.EntityFactory.wolf
import static org.home.game.map.entities.character.Race.HUMAN
import static org.home.game.map.entities.character.Race.ORC
import static org.home.game.map.entities.character.Sex.FEMALE
import static org.home.game.map.entities.character.Sex.MALE

import org.home.game.ConsoleIntegrationSpec
import org.home.game.map.GameMap
import spock.lang.Subject

class GameConsoleViewSpec extends ConsoleIntegrationSpec {

    @Subject
    GameConsoleView view = new GameConsoleView()

    GameMap map = Stub {
        getEntities() >> map().line(road(), wolf(), tree())
                              .line(road(), road(userCharacter('Andrii', HUMAN, MALE)), tree())
                              .line(stone(), road(character('ORC', ORC, FEMALE)), tree())
                              .create()
    }

    void 'map should be drawn'() {
        when:
            view.draw map
        then:
            assertOutput '''\
MAP
-----
| WT|
| UT|
|SOT|
-----
'''
    }

    void 'UnsupportedOperationException should be thrown when draw is called'() {
        when:
            view.draw()
        then:
            UnsupportedOperationException exception = thrown(UnsupportedOperationException)
            exception.message == 'This method is not supported'
    }

    void 'winner notification should be shown'() {
        when:
            view.showWinnerNotification()
        then:
            assertOutput appendLineSeparatorTo('You won')
    }

    void 'game over notification should be shown'() {
        when:
            view.showGameOverNotification()
        then:
            assertOutput appendLineSeparatorTo('Game Over')
    }
}
