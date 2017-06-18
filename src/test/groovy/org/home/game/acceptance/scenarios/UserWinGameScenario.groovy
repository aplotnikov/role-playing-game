package org.home.game.acceptance.scenarios

import org.home.game.ConsoleIntegrationSpec
import org.home.game.Launcher

class UserWinGameScenario extends ConsoleIntegrationSpec {
    void 'user should win the game'() {
        given:
            userInput '1', // Main menu -> Start new game
                      'Andrii', // New character -> Character name: Andrii
                      '1', // New character -> Race: Human
                      '1', // New character -> Sex: male
                      '1', // Map -> Move up
                      '1', // Map -> Move up
                      '1', // Fight with Wolf -> Beat the opponent
                      '2', // Fight with Wolf -> Defense yourself
                      '1', // Fight with Wolf -> Beat the opponent
                      '2', // Map -> Move down
                      '2', // Map -> Move down
                      '3', // Map -> Move right
                      '3', // Map -> Move right
                      '1', // Fight with Bear -> Beat the opponent
                      '1', // Fight with Bear -> Beat the opponent
                      '4', // Map -> Move left
                      '4', // Map -> Move left
                      '4', // Map -> Move left
                      '4', // Map -> Move left
                      '2', // Map -> Move down
                      '2', // Map -> Move down
                      '1', // Fight with Orc -> Beat the opponent
                      '1'  // Fight with Orc -> Beat the opponent
        when:
            Launcher.main()
        then:
            assertOutputEndWith appendLineSeparatorTo('You won')
    }
}
