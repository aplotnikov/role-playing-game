package org.home.game.map.entities;

import org.home.game.map.entities.character.GameCharacter;
import org.home.game.map.entities.character.Race;
import org.home.game.map.entities.character.Sex;
import org.home.game.map.entities.container.ContainerMapEntity;
import org.home.game.map.entities.simple.SimpleMapEntity;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import static org.home.game.map.entities.MapEntityType.BEAR;
import static org.home.game.map.entities.MapEntityType.ROAD;
import static org.home.game.map.entities.MapEntityType.STONE;
import static org.home.game.map.entities.MapEntityType.TREE;
import static org.home.game.map.entities.MapEntityType.WOLF;

public final class MapEntityFactory {

    private MapEntityFactory() {}

    @Nonnull
    public static MapEntity road() {
        return new ContainerMapEntity("Road", ROAD, 0);
    }

    @Nonnull
    public static MapEntity road(@Nonnull MapEntity entity) {
        MapEntity road = road();
        road.take(entity);
        return road;
    }

    @Nonnull
    public static MapEntity wolf() {
        return new SimpleMapEntity("Wolf", WOLF, 10);
    }

    @Nonnull
    public static MapEntity bear() {
        return new SimpleMapEntity("Bear", BEAR, 20);
    }

    @Nonnull
    public static MapEntity tree() {
        return new SimpleMapEntity("Tree", TREE, 0);
    }

    @Nonnull
    public static MapEntity stone() {
        return new SimpleMapEntity("Stone", STONE, 0);
    }

    @Nonnull
    public static MapEntity userCharacter(@Nonnull String name, @Nonnull Race race, @Nonnull Sex sex) {
        return new GameCharacter(name, true, race, sex, 40);
    }

    @Nonnull
    public static MapEntity character(@Nonnull String name, @Nonnull Race race, @Nonnull Sex sex) {
        return character(name, race, sex, 30);
    }

    @Nonnull
    public static MapEntity character(@Nonnull String name, @Nonnull Race race, @Nonnull Sex sex, @Nonnegative int attachPower) {
        return new GameCharacter(name, false, race, sex, attachPower);
    }
}
