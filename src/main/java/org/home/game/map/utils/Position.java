package org.home.game.map.utils;

import java.util.Objects;

import static java.util.Objects.isNull;

public class Position {

    private final int left;

    private final int top;

    private Position(int left, int top) {
        this.left = left;
        this.top = top;
    }

    public static Position of(int left, int top) {
        return new Position(left, top);
    }

    public int getLeft() {
        return left;
    }

    public int getTop() {
        return top;
    }

    @Override
    public boolean equals(Object anotherObject) {
        if (this == anotherObject) {
            return true;
        }

        if (isNull(anotherObject) || getClass() != anotherObject.getClass()) {
            return false;
        }

        Position anotherPosition = (Position) anotherObject;
        return left == anotherPosition.left && top == anotherPosition.top;
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, top);
    }

    @Override
    public String toString() {
        return "Position{left=" + left + ", top=" + top + '}';
    }
}
