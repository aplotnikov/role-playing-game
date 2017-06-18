package org.home.game.acceptance.test

import org.home.game.ConsoleIntegrationSpec
import org.home.game.Launcher

class LoseScenarioAcceptanceTest extends ConsoleIntegrationSpec {
    void 'user should lose the game'() {
        userInput '1', // Main menu -> Start new game
                  'Andrii', // Character name
                  '1', // Race: Human
                  '1', // Sex: male
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
            assertOutputContains 'Game Over'
    }
}
