package org.home.game.map.factory

import static org.home.game.map.entities.MapEntityFactory.userCharacter
import static org.home.game.map.entities.MapEntityType.BEAR
import static org.home.game.map.entities.MapEntityType.CHARACTER
import static org.home.game.map.entities.MapEntityType.ROAD
import static org.home.game.map.entities.MapEntityType.STONE
import static org.home.game.map.entities.MapEntityType.TREE
import static org.home.game.map.entities.MapEntityType.WOLF
import static org.home.game.map.entities.character.Race.HUMAN
import static org.home.game.map.entities.character.Sex.MALE

import org.home.game.map.GameMap
import org.home.game.map.MainGameMap
import org.home.game.map.behaviour.user.UserMovementInput
import org.home.game.map.entities.MapEntity
import org.home.game.map.entities.character.create.NewCharacterFactory
import org.home.game.map.task.TaskCompletionStrategy
import spock.lang.Specification
import spock.lang.Subject

import java.util.function.Predicate

class StaticMapFactorySpec extends Specification {

    NewCharacterFactory newCharacterFactory = Mock()

    UserMovementInput userInput = Stub()

    Predicate<MapEntity> condition = { false }

    TaskCompletionStrategy strategy = Stub()

    @Subject
    StaticMapFactory factory = new StaticMapFactory(newCharacterFactory, userInput, condition, strategy)

    void 'map should be created and user should provide information about his character'() {
        given:
            MapEntity character = userCharacter('Andrii', HUMAN, MALE)
        when:
            GameMap map = factory.create()
        then:
            1 * newCharacterFactory.getGameCharacter() >> character
        and:
            map instanceof MainGameMap
            with(map as MainGameMap) {
                userMovementInput == userInput
                taskDetectionCondition == condition
                taskCompletionStrategy == strategy

                containsUserCharacter()
                !containsTasks()

                entities[0]*.type == [ROAD, ROAD, ROAD, TREE, STONE]
                entities[1]*.type == [ROAD, ROAD, ROAD, TREE, TREE]
                entities[2]*.type == [ROAD, ROAD, ROAD, ROAD, ROAD]
                entities[3]*.type == [ROAD, STONE, ROAD, ROAD, ROAD]
                entities[4]*.type == [ROAD, TREE, ROAD, ROAD, ROAD]

                entities[0][2].innerEntity.isPresent()
                entities[0][2].innerEntity.get().type == WOLF

                entities[2][2].innerEntity.isPresent()
                entities[2][2].innerEntity.get() == character

                entities[2][4].innerEntity.isPresent()
                entities[2][4].innerEntity.get().type == BEAR

                entities[4][0].innerEntity.isPresent()
                entities[4][0].innerEntity.get().type == CHARACTER
            }
    }
}
