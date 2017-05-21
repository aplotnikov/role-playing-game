package org.home.game.map.objects.character;

import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.home.game.map.objects.AbstractMapObject;

import javax.annotation.Nonnull;

import static lombok.AccessLevel.PRIVATE;

@FieldDefaults(level = PRIVATE, makeFinal = true)
@Getter
public class GameCharacter extends AbstractMapObject {

    Race race;

    Sex sex;

    private GameCharacter(@Nonnull String name, @Nonnull Race race, @Nonnull Sex sex, boolean isUserCharacter) {
        super(name, isUserCharacter);
        this.race = race;
        this.sex = sex;
    }

    @Nonnull
    public static GameCharacter userCharacter(@Nonnull String name, @Nonnull Race race, @Nonnull Sex sex) {
        return new GameCharacter(name, race, sex, true);
    }

    @Nonnull
    public static GameCharacter character(@Nonnull String name, @Nonnull Race race, @Nonnull Sex sex) {
        return new GameCharacter(name, race, sex, false);
    }

    @Override
    public void draw() {
        if (isUser()) {
            System.out.print('U');
            return;
        }
        super.draw();
    }

    @Override
    public String toString() {
        return "GameCharacter{" +
                "name=" + getName() +
                ", race=" + race +
                ", sex=" + sex +
                ", isUserCharacter=" + isUser() +
                '}';
    }
}
