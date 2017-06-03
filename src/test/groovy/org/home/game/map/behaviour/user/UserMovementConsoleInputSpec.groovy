package org.home.game.map.behaviour.user

import org.home.game.ConsoleIntegrationSpec
import org.home.game.map.utils.MapPoint
import spock.lang.Subject

class UserMovementConsoleInputSpec extends ConsoleIntegrationSpec {

    @Subject
    MapPoint currentPosition = MapPoint.of(1, 1)

    @Subject
    UserMovementConsoleInput input = new UserMovementConsoleInput()

    void 'new position should be moved up when user decides to move up'() {
        given:
            userInput '1'
        when:
            MapPoint nextPosition = input.getNextPosition(currentPosition)
        then:
            with(nextPosition) {
                left == currentPosition.left
                top == currentPosition.top - 1
            }
        and:
            assertOutput movementMenuOutput()
    }

    void 'new position should be moved down when user decides to move down'() {
        given:
            userInput '2'
        when:
            MapPoint nextPosition = input.getNextPosition(currentPosition)
        then:
            with(nextPosition) {
                left == currentPosition.left
                top == currentPosition.top + 1
            }
        and:
            assertOutput movementMenuOutput()
    }

    void 'new position should be moved right when user decides to move right'() {
        given:
            userInput '3'
        when:
            MapPoint nextPosition = input.getNextPosition(currentPosition)
        then:
            with(nextPosition) {
                left == currentPosition.left + 1
                top == currentPosition.top
            }
        and:
            assertOutput movementMenuOutput()
    }

    void 'new position should be moved left when user decides to move left'() {
        given:
            userInput '4'
        when:
            MapPoint nextPosition = input.getNextPosition(currentPosition)
        then:
            with(nextPosition) {
                left == currentPosition.left - 1
                top == currentPosition.top
            }
        and:
            assertOutput movementMenuOutput()
    }

    private static String movementMenuOutput() {
        """\
Choose character movement:
1. Move up
2. Move down
3. Move right
4. Move left
Put operation's number which you want to do: 
"""
    }
}
