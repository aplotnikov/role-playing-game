package org.home.game.map.entities.character.create

import static org.home.game.map.entities.MapEntityType.CHARACTER
import static org.home.game.map.entities.character.Race.HUMAN
import static org.home.game.map.entities.character.Sex.MALE

import org.home.game.map.entities.MapEntity
import org.home.game.map.entities.character.GameCharacter
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

@Unroll
class NewCharacterPresenterSpec extends Specification {

    @Shared
    String characterName = 'Andrii'

    NewCharacterView view = Mock()

    @Subject
    NewCharacterPresenter presenter = new NewCharacterPresenter(view)

    void 'presenter should be set as delegate into view during object creation'() {
        when:
            new NewCharacterPresenter(view)
        then:
            1 * view.setDelegate(_ as NewCharacterPresenter)
        and:
            0 * _
    }

    void 'game character should be created when all parameters are configured'() {
        given:
            presenter.onChosen(MALE)
            presenter.onChosen(HUMAN)
            presenter.onChosen(characterName)
            presenter.onCompleted()
        when:
            MapEntity character = presenter.getGameCharacter()
        then:
            character instanceof GameCharacter
            with(character as GameCharacter) {
                type == CHARACTER
                name == characterName
                race == HUMAN
                sex == MALE
                isUser()
            }
    }

    void 'NullPointerException should be thrown when one of the character parameters is null (#name, #race, #sex)'() {
        given:
            if (name) {
                presenter.onChosen(name)
            }
            if (race) {
                presenter.onChosen(race)
            }
        when:
            presenter.onCompleted()
        then:
            NullPointerException exception = thrown(NullPointerException)
            exception.message == message
        where:
            name          | race  | sex  || message
            null          | null  | null || 'It is impossible to create an instance of character without name parameter'
            characterName | null  | null || 'It is impossible to create an instance of character without race parameter'
            characterName | HUMAN | null || 'It is impossible to create an instance of character without sex parameter'
    }
}
