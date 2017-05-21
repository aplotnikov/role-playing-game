package org.home.game.menu

import org.home.game.play.Game
import org.home.game.play.GameFactory
import spock.lang.Specification
import spock.lang.Subject

class MenuPresenterSpec extends Specification {

    MenuView view = Mock()

    GameFactory factory = Mock()

    @Subject
    MenuPresenter presenter = new MenuPresenter(view, factory)

    Game game = Mock()

    void 'presenter should be set as delegate into view during object creation'() {
        when:
            new MenuPresenter(view, factory)
        then:
            1 * view.setDelegate(_ as MenuPresenter)
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
