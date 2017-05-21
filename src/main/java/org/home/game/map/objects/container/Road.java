package org.home.game.map.objects.container;

import lombok.experimental.FieldDefaults;
import org.home.game.map.objects.AbstractMapObject;
import org.home.game.map.objects.MapObject;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static lombok.AccessLevel.PRIVATE;

@FieldDefaults(level = PRIVATE)
public class Road extends AbstractMapObject {

    @CheckForNull
    MapObject object;

    private Road() {
        super("Road", false);
    }

    private Road(@Nonnull MapObject object) {
        this();
        this.object = object;
    }

    @Nonnull
    public static Road road() {
        return new Road();
    }

    @Nonnull
    public static Road road(@Nonnull MapObject object) {
        return new Road(object);
    }

    @Override
    public void draw() {
        if (isNull(object)) {
            System.out.print(' ');
            return;
        }
        object.draw();
    }

    @Override
    public boolean canContainAnotherObject() {
        return true;
    }

    @Override
    public boolean containAnotherObject() {
        return nonNull(object);
    }

    @Override
    public void take(@Nonnull MapObject anotherObject) {
        object = anotherObject;
    }

    @Override
    public void clear() {
        object = null;
    }

    @Override
    public String toString() {
        return "Road{name='" + getName() + '\'' + ", isUserCharacter=" + isUser() + ", object=" + object + '}';
    }
}
