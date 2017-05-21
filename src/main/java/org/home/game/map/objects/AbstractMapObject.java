package org.home.game.map.objects;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.annotation.Nonnull;

import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public abstract class AbstractMapObject implements MapObject {

    @NonNull
    String name;

    boolean isUserCharacter;

    @Override
    public void draw() {
        System.out.print(name.charAt(0));
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

    @Override
    public boolean canContainAnotherObject() {
        return false;
    }

    @Override
    public boolean containAnotherObject() {
        return false;
    }

    @Override
    public void take(@Nonnull MapObject anotherObject) {
        throw new UnsupportedOperationException("This method is not supported.");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("This method is not supported.");
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{name='" + name + '\'' + ", isUserCharacter=" + isUserCharacter + '}';
    }
}
