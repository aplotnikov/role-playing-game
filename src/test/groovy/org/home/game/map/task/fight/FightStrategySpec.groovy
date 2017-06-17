package org.home.game.map.task.fight

import org.home.game.map.entities.Entity
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

@Unroll
class FightStrategySpec extends Specification {

    FightView view = Mock()

    @Subject
    FightStrategy strategy = new FightStrategy(view)

    Entity user = Mock()

    Entity enemy = Mock()

    void 'presenter should be set as delegate into view during object creation'() {
        when:
            new FightStrategy(view)
        then:
            1 * view.setDelegate(_ as FightStrategy)
        and:
            0 * _
    }

    void 'view should be drawn when complete method is called and entities are alive'() {
        when:
            strategy.complete(user, enemy)
        then:
            1 * view.draw()
        and:
            1 * user.alive >> true
            1 * enemy.alive >> true
        and:
            0 * _
    }

    void 'view should be not drawn when complete method is called and one of entities is not alive (#userIsAlive, #enemyIsAlive)'() {
        when:
            strategy.complete(user, enemy)
        then:
            user.alive >> userIsAlive
            enemy.alive >> enemyIsAlive
        and:
            0 * _
        where:
            userIsAlive | enemyIsAlive
            false       | false
            true        | false
            false       | true
    }

    void 'both entities should attack, view is drawn when both entities are alive and user attacks'() {
        given:
            strategy.complete(user, enemy)
        when:
            strategy.onUserAttack()
        then:
            1 * enemy.alive >> true
            1 * enemy.isBeatenBy(user)
        and:
            1 * user.alive >> true
            1 * user.isBeatenBy(enemy)
        and:
            1 * view.draw()
        and:
            0 * _
    }

    void 'both entities should attack, view is not drawn when one of the entities is not alive (#userIsAlive, #enemyIsAlive) when user attacks'() {
        given:
            strategy.complete(user, enemy)
        when:
            strategy.onUserAttack()
        then:
            enemy.alive >> enemyIsAlive
            1 * enemy.isBeatenBy(user)
        and:
            user.alive >> userIsAlive
            1 * user.isBeatenBy(enemy)
        and:
            0 * _
        where:
            userIsAlive | enemyIsAlive
            false       | false
            true        | false
            false       | true
    }

    void 'user should defend, enemy attacks, view is drawn when both entities are alive and user defends'() {
        given:
            strategy.complete(user, enemy)
        when:
            strategy.onUserDefend()
        then:
            1 * enemy.alive >> true
        and:
            1 * user.alive >> true
            1 * user.defense()
            1 * user.isBeatenBy(enemy)
        and:
            1 * view.draw()
        and:
            0 * _
    }

    void 'user should defend, enemy attacks, view is not drawn when one of the entities is not alive (#userIsAlive, #enemyIsAlive) and user defends'() {
        given:
            strategy.complete(user, enemy)
        when:
            strategy.onUserDefend()
        then:
            enemy.alive >> enemyIsAlive
        and:
            user.alive >> userIsAlive
            1 * user.defense()
            1 * user.isBeatenBy(enemy)
        and:
            0 * _
        where:
            userIsAlive | enemyIsAlive
            false       | false
            true        | false
            false       | true
    }


    void 'user should do nothing, enemy attacks when view is drawn when both entities are alive and user does nothing'() {
        given:
            strategy.complete(user, enemy)
        when:
            strategy.onDoNothing()
        then:
            1 * enemy.alive >> true
        and:
            1 * user.alive >> true
            1 * user.isBeatenBy(enemy)
        and:
            1 * view.draw()
        and:
            0 * _
    }

    void 'user should do nothing, enemy attacks, view is not drawn when one of the entities is not alive (#userIsAlive, #enemyIsAlive) user does nothing'() {
        given:
            strategy.complete(user, enemy)
        when:
            strategy.onDoNothing()
        then:
            enemy.alive >> enemyIsAlive
        and:
            user.alive >> userIsAlive
            1 * user.isBeatenBy(enemy)
        and:
            0 * _
        where:
            userIsAlive | enemyIsAlive
            false       | false
            true        | false
            false       | true
    }
}
