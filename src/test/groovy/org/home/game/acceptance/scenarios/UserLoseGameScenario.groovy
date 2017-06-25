package org.home.game.acceptance.scenarios

import org.home.game.ConsoleIntegrationSpec
import org.home.game.Launcher

class UserLoseGameScenario extends ConsoleIntegrationSpec {
    void 'user should lose the game'() {
        given:
            userInput '1', // Main menu -> Start new game
                      'Andrii', // New character -> Character name: Andrii
                      '1', // New character -> Race: Human
                      '1', // New character -> Sex: male
                      '3', // Map -> Move right
                      '3', // Map -> Move right
                      '3', // Fight with Bear -> Do nothing
                      '3', // Fight with Bear -> Do nothing
                      '3', // Fight with Bear -> Do nothing
                      '3', // Fight with Bear -> Do nothing
                      '3'  // Fight with Bear -> Do nothing
        when:
            Launcher.main()
        then:
            assertOutputEndWith appendLineSeparatorTo('Game Over')
    }
}
