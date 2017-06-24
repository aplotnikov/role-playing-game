package org.home.game.map.entities.character;

import org.home.game.map.entities.SimpleEntity;

import java.util.Objects;

import static java.util.Objects.isNull;
import static org.home.game.map.entities.EntityType.CHARACTER;

public class GameCharacter extends SimpleEntity {

    private final boolean isUserCharacter;

    private final Race race;

    private final Sex sex;

    public GameCharacter(String name, boolean isUserCharacter, Race race, Sex sex, int attackPower) {
        super(name, CHARACTER, attackPower);
        this.isUserCharacter = isUserCharacter;
        this.race = race;
        this.sex = sex;
    }

    @Override
    public boolean isUser() {
        return isUserCharacter;
    }

    public Race getRace() {
        return race;
    }

    public Sex getSex() {
        return sex;
    }

    @Override
    public boolean equals(Object anotherObject) {
        if (this == anotherObject) {
            return true;
        }

        if (isNull(anotherObject) || getClass() != anotherObject.getClass()) {
            return false;
        }

        GameCharacter anotherCharacter = (GameCharacter) anotherObject;
        return super.equals(anotherCharacter)
                && isUserCharacter == anotherCharacter.isUserCharacter
                && race == anotherCharacter.race
                && sex == anotherCharacter.sex;
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
                + ", defended=" + isDefended()
                + '}';
    }
}
