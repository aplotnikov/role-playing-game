package org.home.game.menu

import org.home.game.play.Game
import org.home.game.play.GameFactory
import spock.lang.Specification
import spock.lang.Subject

class MainMenuPresenterSpec extends Specification {

    MainMenuView view = Mock()

    GameFactory factory = Mock()

    @Subject
    MainMenuPresenter presenter = new MainMenuPresenter(view, factory)

    Game game = Mock()

    void 'presenter should be set as delegate into view during object creation'() {
        when:
            new MainMenuPresenter(view, factory)
        then:
            1 * view.setDelegate(_ as MainMenuPresenter)
        and:
            0 * _
    }

    void 'new game should be started when user chooses start option'() {
        when:
            presenter.onStartChosen()
        then:
            1 * factory.create() >> game
        and:
            1 * game.start()
        and:
            0 * _
    }

    void 'old game should be resumed when user chooses resume option'() {
        when:
            presenter.onResumeChosen()
        then:
            1 * factory.resume() >> game
        and:
            1 * game.start()
        and:
            0 * _
    }
}
