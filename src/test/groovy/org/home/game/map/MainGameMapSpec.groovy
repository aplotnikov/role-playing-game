package org.home.game.map

import static org.home.game.map.entities.MapEntityFactory.road
import static org.home.game.map.entities.MapEntityFactory.tree
import static org.home.game.map.entities.MapEntityFactory.userCharacter
import static org.home.game.map.entities.MapEntityFactory.wolf
import static org.home.game.map.entities.character.Race.HUMAN
import static org.home.game.map.entities.character.Sex.MALE

import org.home.game.map.behaviour.user.UserMovementInput
import org.home.game.map.entities.MapEntity
import org.home.game.map.task.TaskCompletionStrategy
import org.home.game.map.utils.Position
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

import java.util.function.Predicate

@Unroll
class MainGameMapSpec extends Specification {

    List<List<MapEntity>> entities = [[road()]]

    Predicate<MapEntity> taskDetectionCondition = Stub()

    UserMovementInput userMovementInput = Stub()

    TaskCompletionStrategy taskCompletionStrategy = Mock()

    @Subject
    MainGameMap map = new MainGameMap(entities, userMovementInput, taskDetectionCondition, taskCompletionStrategy)

    void 'map should contain user character when entity contains user character'() {
        given:
            entities[0][0].take(userCharacter('Andrii', HUMAN, MALE))
        expect:
            map.containsUserCharacter()
    }

    void 'map should not contain user character when entity does not contain user character'() {
        expect:
            !map.containsUserCharacter()
    }

    void 'map should contain tasks when entity contains tasks'() {
        given:
            taskDetectionCondition.test(_ as MapEntity) >> entityContainsTasks
        expect:
            map.containsTasks() == entityContainsTasks
        where:
            entityContainsTasks << [true, false]
    }

    void 'IllegalStateException should be thrown when there are no user character'() {
        when:
            map.goToNextIteration()
        then:
            IllegalStateException exception = thrown(IllegalStateException)
            exception.message == 'It is impossible to continue game when no user character on the map'
        and:
            0 * _
    }

    void 'nothing should happen when next user position is invalid - #nextPosition'() {
        given:
            entities[0] << road()
            entities << [road(), road(userCharacter('Andrii', HUMAN, MALE))]
        and:
            userMovementInput.getNextPosition(_ as Position) >> nextPosition
        when:
            map.goToNextIteration()
        then:
            entities[1][1].containUserCharacter()
        and:
            0 * _
        where:
            nextPosition << [
                    Position.of(-1, 0),
                    Position.of(0, -1),
                    Position.of(2, 0),
                    Position.of(0, 2)
            ]
    }

    void 'nothing should happen when next user position can not contain another entity'() {
        given:
            entities[0] << tree()
            entities << [road(), road(userCharacter('Andrii', HUMAN, MALE))]
        and:
            userMovementInput.getNextPosition(_ as Position) >> Position.of(1, 0)
        when:
            map.goToNextIteration()
        then:
            entities[1][1].containUserCharacter()
        and:
            0 * _
    }

    void 'user should be moved to another position when that position is empty - #nextPosition'() {
        given:
            entities[0] << road() << road()
            entities << [road(), road(userCharacter('Andrii', HUMAN, MALE)), road()]
            entities << [road(), road(), road()]
        and:
            userMovementInput.getNextPosition(_ as Position) >> nextPosition
        when:
            map.goToNextIteration()
        then:
            !entities[1][1].containUserCharacter()
        and:
            entities[nextPosition.top][nextPosition.left].containUserCharacter()
        and:
            0 * _
        where:
            nextPosition << [
                    Position.of(1, 0),
                    Position.of(1, 2),
                    Position.of(0, 1),
                    Position.of(2, 1)
            ]
    }

    void 'task completion strategy should be processed when user meets a task'() {
        given:
            MapEntity wolf = wolf()
            MapEntity character = userCharacter('Andrii', HUMAN, MALE)
        and:
            entities[0] << road()
            entities << [road(wolf), road(character)]
        and:
            userMovementInput.getNextPosition(_ as Position) >> Position.of(0, 1)
        and:
            taskDetectionCondition.test(_ as MapEntity) >> true
        when:
            map.goToNextIteration()
        then:
            entities[1][0].containUserCharacter()
            !entities[1][1].containUserCharacter()
        and:
            1 * taskCompletionStrategy.complete(character, wolf) >> {
                MapEntity user, MapEntity enemy ->
                    user.defense()
                    enemy.defense()
            }
        and:
            !wolf.defended
            !character.defended
        and:
            0 * _
    }

    void 'user character should be removed when the character is not alive'() {
        given:
            MapEntity character = userCharacter('Andrii', HUMAN, MALE)
            character.isBeatenBy(Stub(MapEntity) { getAttackPower() >> 100 })
        and:
            entities[0] << road()
            entities << [road(wolf()), road(character)]
        and:
            userMovementInput.getNextPosition(_ as Position) >> Position.of(0, 1)
        when:
            map.goToNextIteration()
        then:
            !entities[1][0].containUserCharacter()
            !entities[1][1].containUserCharacter()
        and:
            0 * _
    }
}
