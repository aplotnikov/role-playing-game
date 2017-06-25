package org.home.game.common.mvp

import spock.lang.Specification
import spock.lang.Subject

class AbstractPresenterSpec extends Specification {

    View view = Mock()

    @Subject
    AbstractPresenter<View> presenter = new AbstractPresenter<View>(view) { }

    void 'draw method on presenter should be called'() {
        when:
            presenter.show()
        then:
            1 * view.draw()
        and:
            0 * _
    }
}
