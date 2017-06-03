package org.home.game.map.utils;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Objects;

public class MapPoint {

    private final int left;

    private final int top;

    private MapPoint(@Nonnegative int left, @Nonnegative int top) {
        this.left = left;
        this.top = top;
    }

    @Nonnull
    public static MapPoint of(@Nonnegative int left, @Nonnegative int top) {
        return new MapPoint(left, top);
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

        MapPoint mapPoint = (MapPoint) o;
        return left == mapPoint.left && top == mapPoint.top;
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, top);
    }

    @Override
    public String toString() {
        return "MapPoint{" + "left=" + left + ", top=" + top + '}';
    }
}
