package org.home.game.map.factory

import static org.home.game.map.objects.character.GameCharacter.userCharacter
import static org.home.game.map.objects.character.Race.HUMAN
import static org.home.game.map.objects.character.Sex.MALE

import org.home.game.map.Map
import org.home.game.map.objects.animals.Bear
import org.home.game.map.objects.animals.Wolf
import org.home.game.map.objects.character.GameCharacter
import org.home.game.map.objects.character.create.NewCharacterPresenter
import org.home.game.map.objects.container.Road
import org.home.game.map.objects.immovable.Stone
import org.home.game.map.objects.immovable.Tree
import spock.lang.Specification
import spock.lang.Subject

class StaticMapFactorySpec extends Specification {

    NewCharacterPresenter newCharacterPresenter = Mock()

    @Subject
    StaticMapFactory factory = new StaticMapFactory(newCharacterPresenter)

    void 'map should be created and user should provide information about his character'() {
        given:
            GameCharacter character = userCharacter('name', HUMAN, MALE)
        when:
            Map map = factory.create()
        then:
            1 * newCharacterPresenter.show()
            1 * newCharacterPresenter.getGameCharacter() >> character
        and:
            with(map) {
                objects[0][0] instanceof Road
                objects[0][1] instanceof Road
                objects[0][2] instanceof Wolf
                objects[0][3] instanceof Tree
                objects[0][4] instanceof Stone

                objects[1][0] instanceof Road
                objects[1][1] instanceof Road
                objects[1][2] instanceof Road
                objects[1][3] instanceof Tree
                objects[1][4] instanceof Tree

                objects[2][0] instanceof Road
                objects[2][1] instanceof Road
                objects[2][2] == character
                objects[2][3] instanceof Road
                objects[2][4] instanceof Bear

                objects[3][0] instanceof Road
                objects[3][1] instanceof Stone
                objects[3][2] instanceof Road
                objects[3][3] instanceof Road
                objects[3][4] instanceof Road

                objects[4][0] instanceof Road
                objects[4][1] instanceof Tree
                objects[4][2] instanceof Road
                objects[4][3] instanceof Road
                objects[4][4] instanceof Road
            }
    }
}
