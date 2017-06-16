package org.home.game.map.entities.character;

import org.home.game.map.entities.simple.SimpleMapEntity;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Objects;

import static org.home.game.map.entities.MapEntityType.CHARACTER;

public class GameCharacter extends SimpleMapEntity {

    private final boolean isUserCharacter;

    private final Race race;

    private final Sex sex;

    public GameCharacter(@Nonnull String name,
                         boolean isUserCharacter,
                         @Nonnull Race race,
                         @Nonnull Sex sex,
                         @Nonnegative int attackPower) {
        super(name, CHARACTER, attackPower);
        this.isUserCharacter = isUserCharacter;
        this.race = race;
        this.sex = sex;
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        GameCharacter that = (GameCharacter) o;
        return super.equals(that)
                && isUserCharacter == that.isUserCharacter
                && race == that.race
                && sex == that.sex;
    }

    @Override
    public int hashCode() {
        return 31 * super.hashCode() + Objects.hash(isUserCharacter, race, sex);
    }

    @Override
    public String toString() {
        return "GameCharacter{"
                + "name='" + getName() + '\''
                + ", type=" + getType()
                + ", isUserCharacter=" + isUserCharacter
                + ", race=" + race
                + ", sex=" + sex
                + ", health=" + getHealth()
                + ", attackPower=" + getAttackPower()
                + '}';
    }
}
