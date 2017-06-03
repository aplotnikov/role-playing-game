package org.home.game.map.entities

import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Subject

import javax.annotation.Nonnull
import java.util.function.Predicate

class MapEntitySpec extends Specification {

    @Shared
    Predicate<MapEntity> foreverTrueCondition = { true }

    @Shared
    Predicate<MapEntity> foreverFalseCondition = { false }

    @Subject
    MapEntity entity = new MapEntity() {

        @Nonnull
        @Override
        String getName() {
            return null
        }

        @Override
        boolean isUser() {
            return false
        }

        @Override
        void take(@Nonnull MapEntity anotherEntity) {}

        @Override
        void clear() {}

        @Nonnull
        @Override
        MapEntityType getType() {
            return null
        }
    }

    void 'entity should be not able to contain another entities'() {
        expect:
            !entity.canContainAnotherEntity()
    }

    void 'entity should contain no another entities'() {
        expect:
            !entity.containAnotherEntity()
        and:
            entity.containAnotherEntity() == entity.innerEntity.present
    }

    void 'entity should contain no tasks'() {
        expect:
            entity.containTasks(foreverTrueCondition)
        and:
            !entity.containTasks(foreverFalseCondition)
    }
}
