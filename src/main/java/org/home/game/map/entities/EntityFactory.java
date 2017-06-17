package org.home.game.map.entities;

import org.home.game.map.entities.character.GameCharacter;
import org.home.game.map.entities.character.Race;
import org.home.game.map.entities.character.Sex;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import static org.home.game.map.entities.EntityType.BEAR;
import static org.home.game.map.entities.EntityType.ROAD;
import static org.home.game.map.entities.EntityType.STONE;
import static org.home.game.map.entities.EntityType.TREE;
import static org.home.game.map.entities.EntityType.WOLF;

public final class EntityFactory {

    private EntityFactory() {}

    @Nonnull
    public static Entity road() {
        return new ContainerEntity("Road", ROAD, 0);
    }

    @Nonnull
    public static Entity road(@Nonnull Entity entity) {
        Entity road = road();
        road.take(entity);
        return road;
    }

    @Nonnull
    public static Entity wolf() {
        return new SimpleEntity("Wolf", WOLF, 10);
    }

    @Nonnull
    public static Entity bear() {
        return new SimpleEntity("Bear", BEAR, 20);
    }

    @Nonnull
    public static Entity tree() {
        return new SimpleEntity("Tree", TREE, 0);
    }

    @Nonnull
    public static Entity stone() {
        return new SimpleEntity("Stone", STONE, 0);
    }

    @Nonnull
    public static Entity userCharacter(@Nonnull String name, @Nonnull Race race, @Nonnull Sex sex) {
        return new GameCharacter(name, true, race, sex, 40);
    }

    @Nonnull
    public static Entity character(@Nonnull String name, @Nonnull Race race, @Nonnull Sex sex) {
        return character(name, race, sex, 30);
    }

    @Nonnull
    public static Entity character(@Nonnull String name, @Nonnull Race race, @Nonnull Sex sex, @Nonnegative int attachPower) {
        return new GameCharacter(name, false, race, sex, attachPower);
    }
}
