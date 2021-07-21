package com.nab.test.utils;

import static com.nab.test.utils.LongUtils.doubleToLong98;
import static com.nab.test.utils.LongUtils.long98ToDouble;
import static com.nab.test.utils.LongUtils.LONG_98_EPSILON;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LongUtilsTest {

    @Test
    public void testConversions() {
        doTest(0L, 0);
        doTest(1_000L, 0.00001);
        doTest(1_000_000L, 0.01);
        doTest(100_000_000L, 1.0);
        doTest(100_000_001L, 1.00000001);
        doTest(79_820_000L, 0.7982);
        doTest(Long.MIN_VALUE, Double.NaN);
    }

    private void doTest(long l98, double d) {
        assertEquals(d, long98ToDouble(l98), LONG_98_EPSILON);
        assertEquals(l98, doubleToLong98(d));
    }
}
