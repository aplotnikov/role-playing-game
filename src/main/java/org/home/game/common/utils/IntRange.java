package org.home.game.common.utils;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

public class IntRange {

    private final int min;

    private final int max;

    private IntRange(@Nonnegative int min, @Nonnegative int max) {
        this.min = min;
        this.max = max;
    }

    @Nonnull
    public static IntRange of(@Nonnegative int min, @Nonnegative int max) {
        if (min > max) {
            throw new IllegalStateException("Min value is greater than max. Min: " + min + ", Max: " + max);
        }
        return new IntRange(min, max);
    }

    public boolean contains(@Nonnegative int value) {
        return value >= min && value <= max;
    }

    @Override
    public String toString() {
        return "IntRange{min=" + min + ", max=" + max + '}';
    }
}
