package org.home.game.map.behaviour.user

import static org.home.game.map.GameMapBuilder.map
import static org.home.game.map.entities.MapEntityFactory.road
import static org.home.game.map.entities.MapEntityFactory.tree
import static org.home.game.map.entities.MapEntityFactory.userCharacter
import static org.home.game.map.entities.MapEntityFactory.wolf
import static org.home.game.map.entities.character.Race.HUMAN
import static org.home.game.map.entities.character.Sex.MALE

import org.home.game.map.entities.MapEntity
import org.home.game.map.utils.MapPoint
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

@Unroll
class UserBehaviourStrategySpec extends Specification {

    UserMovementInput userMovementInput = Stub()

    @Subject
    UserBehaviourStrategy strategy = new UserBehaviourStrategy(userMovementInput)

    void 'user character should stay on the same position when new position is out of map range - #nextPosition'() {
        given:
            List<List<MapEntity>> entities = entities()
        and:
            userMovementInput.getNextPosition(_ as MapPoint) >> nextPosition
        expect:
            entities[1][1].containUserCharacter()
        when:
            strategy.process(entities)
        then:
            entities[1][1].containUserCharacter()
        where:
            nextPosition << [
                    MapPoint.of(-1, 1),
                    MapPoint.of(4, 1),
                    MapPoint.of(1, -1),
                    MapPoint.of(1, 4),
            ]
    }

    void 'user character should stay on the same position when new position can not contain another entity'() {
        given:
            List<List<MapEntity>> entities = entities()
        and:
            userMovementInput.getNextPosition(_ as MapPoint) >> MapPoint.of(1, 0)
        expect:
            entities[1][1].containUserCharacter()
        when:
            strategy.process(entities)
        then:
            entities[1][1].containUserCharacter()
    }

    void 'user character should stay on the same position when new position can contain another entity but inner entity is present'() {
        given:
            List<List<MapEntity>> entities = entities()
        and:
            userMovementInput.getNextPosition(_ as MapPoint) >> MapPoint.of(1, 2)
        expect:
            entities[1][1].containUserCharacter()
        when:
            strategy.process(entities)
        then:
            entities[1][1].containUserCharacter()
    }

    void 'user character should be moved to the new position'() {
        given:
            List<List<MapEntity>> entities = entities()
        and:
            userMovementInput.getNextPosition(_ as MapPoint) >> MapPoint.of(0, 1)
        expect:
            entities[1][1].containUserCharacter()
            !entities[1][0].containUserCharacter()
        when:
            strategy.process(entities)
        then:
            !entities[1][1].containUserCharacter()
            entities[1][0].containUserCharacter()
    }

    private static List<List<MapEntity>> entities() {
        map().line(road(), tree(), road())
             .line(road(), road(userCharacter('Andrii', HUMAN, MALE)), road())
             .line(road(), road(wolf()), road())
             .create()
    }
}
