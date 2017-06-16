package org.home.game.map.utils;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Objects;

public class Position {

    private final int left;

    private final int top;

    private Position(@Nonnegative int left, @Nonnegative int top) {
        this.left = left;
        this.top = top;
    }

    @Nonnull
    public static Position of(@Nonnegative int left, @Nonnegative int top) {
        return new Position(left, top);
    }

    @Nonnegative
    public int getLeft() {
        return left;
    }

    @Nonnegative
    public int getTop() {
        return top;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Position position = (Position) o;
        return left == position.left && top == position.top;
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, top);
    }

    @Override
    public String toString() {
        return "Position{" + "left=" + left + ", top=" + top + '}';
    }
}
