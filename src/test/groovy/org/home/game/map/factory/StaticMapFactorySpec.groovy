package org.home.game.map.factory

import static org.home.game.map.entities.EntityFactory.road
import static org.home.game.map.entities.EntityFactory.tree
import static org.home.game.map.entities.EntityFactory.userCharacter
import static org.home.game.map.entities.EntityType.BEAR
import static org.home.game.map.entities.EntityType.CHARACTER
import static org.home.game.map.entities.EntityType.ROAD
import static org.home.game.map.entities.EntityType.STONE
import static org.home.game.map.entities.EntityType.TREE
import static org.home.game.map.entities.EntityType.WOLF
import static org.home.game.map.entities.character.Race.HUMAN
import static org.home.game.map.entities.character.Sex.MALE

import org.home.game.map.GameMap
import org.home.game.map.MainGameMap
import org.home.game.map.behaviour.user.UserMovementInput
import org.home.game.map.entities.Entity
import org.home.game.map.entities.character.create.NewCharacterFactory
import org.home.game.map.factory.resume.Restorer
import org.home.game.map.task.TaskCompletionStrategy
import spock.lang.Specification
import spock.lang.Subject

import java.util.function.Predicate

class StaticMapFactorySpec extends Specification {

    NewCharacterFactory newCharacterFactory = Mock()

    UserMovementInput userInput = Stub()

    Predicate<Entity> condition = { false }

    TaskCompletionStrategy strategy = Stub()

    Restorer restorer = Stub()

    @Subject
    StaticMapFactory factory = new StaticMapFactory(newCharacterFactory, userInput, condition, strategy, restorer)

    void 'map should be created and user should provide information about his character'() {
        given:
            Entity character = userCharacter('Andrii', HUMAN, MALE)
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

    void 'game should be restored by restorer'() {
        given:
            List<Entity> mapEntities = [road(), tree()]
        and:
            restorer.restore() >> mapEntities
        when:
            GameMap map = factory.restore()
        then:
            map instanceof MainGameMap
            with(map as MainGameMap) {
                userMovementInput == userInput
                taskDetectionCondition == condition
                taskCompletionStrategy == strategy

                entities == mapEntities
            }
    }
}
