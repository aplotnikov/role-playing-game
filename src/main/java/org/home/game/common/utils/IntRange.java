package org.home.game.common.utils;

public class IntRange {

    private final int min;

    private final int max;

    private IntRange(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public static IntRange of(int min, int max) {
        if (min > max) {
            throw new IllegalStateException("Min value is greater than max. Min: " + min + ", Max: " + max);
        }
        return new IntRange(min, max);
    }

    public boolean contains(int value) {
        return value >= min && value <= max;
    }

    @Override
    public String toString() {
        return "IntRange{min=" + min + ", max=" + max + '}';
    }
}
