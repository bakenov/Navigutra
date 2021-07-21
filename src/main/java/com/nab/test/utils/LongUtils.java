package com.nab.test.utils;

/**
 *  Converts positive long numbers
 */
public class LongUtils {

    public static final long LONG_NULL = Long.MIN_VALUE;
    public static final double LONG_98_EPSILON = 0.00000001;
    public static final double LONG_98_SCALE = 1e8;

    public static long doubleToLong98(double value) {
        return Double.isNaN(value) ? LONG_NULL : Math.round(value * LONG_98_SCALE);
    }

    public static double long98ToDouble(long value) {
        return value == LONG_NULL ? Double.NaN : value / LONG_98_SCALE;
    }
}
