package org.home.game.map.factory.resume

import static org.home.game.map.entities.EntityFactory.road
import static org.home.game.map.entities.EntityFactory.tree

import org.home.game.map.entities.Entity
import org.home.game.map.factory.resume.marshaller.Marshaller
import org.junit.Rule
import org.junit.rules.TemporaryFolder
import spock.lang.Specification
import spock.lang.Subject

class RestorePresenterSpec extends Specification {

    @Rule
    TemporaryFolder folder = new TemporaryFolder()

    RestoreView view = Mock()

    Marshaller marshaller = Mock()

    @Subject
    RestorePresenter presenter = new RestorePresenter(view, marshaller)

    void 'marshaller should unmarshall file from provided path when the path is correct'() {
        given:
            File file = folder.newFile('my_file.txt')
        and:
            List<List<Entity>> entities = [[road(), tree()]]
        when:
            List<List<Entity>> result = presenter.restore()
        then:
            result == entities
        and:
            2 * view.draw()
            view.path >>> ['not_exist_file.txt', file.path]
        and:
            1 * marshaller.unmarshall(file.path) >> entities
        and:
            0 * _
    }
}
