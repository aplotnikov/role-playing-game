package org.home.game.map

import static org.home.game.map.GameMapBuilder.map

import org.home.game.map.behaviour.GameStrategy
import org.home.game.map.entities.MapEntity
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

import java.util.function.Predicate

@Unroll
class MainGameMapSpec extends Specification {

    MapEntity entity = Stub()

    List<List<MapEntity>> entities = map().line(entity).create()

    GameStrategy userBehaviour = Mock()

    GameStrategy gameBehaviour = Mock()

    Predicate<MapEntity> taskDetectionCondition = Stub()

    @Subject
    MainGameMap map = new MainGameMap(entities, userBehaviour, gameBehaviour, taskDetectionCondition)

    void 'map should contain user character when entity contains user character'() {
        given:
            entity.containUserCharacter() >> entityContainsUserCharacter
        expect:
            map.containsUserCharacter() == entityContainsUserCharacter
        where:
            entityContainsUserCharacter << [true, false]
    }

    void 'map should contain tasks when entity contains tasks'() {
        given:
            entity.containTasks(taskDetectionCondition) >> entityContainsTasks
        expect:
            map.containsTasks() == entityContainsTasks
        where:
            entityContainsTasks << [true, false]
    }

    void 'map should process user and game behaviour on the next iteration'() {
        when:
            map.goToNextIteration()
        then:
            1 * userBehaviour.process(entities)
            1 * gameBehaviour.process(entities)
        and:
            0 * _
    }
}
