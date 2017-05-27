package org.home.game.map.entities.character;

import org.home.game.map.entities.MapEntity;
import org.home.game.map.entities.MapEntityType;

import javax.annotation.Nonnull;
import java.util.Objects;

import static org.home.game.map.entities.MapEntityType.CHARACTER;

public class GameCharacter implements MapEntity {

    private final String name;

    private final boolean isUserCharacter;

    private final Race race;

    private final Sex sex;

    public GameCharacter(@Nonnull String name, boolean isUserCharacter, @Nonnull Race race, @Nonnull Sex sex) {
        this.name = name;
        this.isUserCharacter = isUserCharacter;
        this.race = race;
        this.sex = sex;
    }

    @Nonnull
    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isUser() {
        return isUserCharacter;
    }

    @Nonnull
    public Race getRace() {
        return race;
    }

    @Nonnull
    public Sex getSex() {
        return sex;
    }

    @Override
    public boolean canContainAnotherEntity() {
        return false;
    }

    @Override
    public boolean containAnotherEntity() {
        return false;
    }

    @Override
    public void take(@Nonnull MapEntity anotherEntity) {
        throw new UnsupportedOperationException("This method is not supported.");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("This method is not supported.");
    }

    @Nonnull
    @Override
    public MapEntityType getType() {
        return CHARACTER;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GameCharacter that = (GameCharacter) o;
        return isUserCharacter == that.isUserCharacter
                && Objects.equals(name, that.name)
                && race == that.race
                && sex == that.sex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, isUserCharacter, race, sex);
    }

    @Override
    public String toString() {
        return "GameCharacter{"
                + "name='" + name + '\''
                + ", isUserCharacter=" + isUserCharacter
                + ", race=" + race
                + ", sex=" + sex
                + '}';
    }
}
