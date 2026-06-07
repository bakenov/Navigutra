package dataandplot.data.model.data.range;

import dataandplot.data.range.MinMaxDouble;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MinMaxDoubleTest {

    @Test
    void test() {
        MinMaxDouble range = new MinMaxDouble(0, 0, 0, 0);
        assertEquals(0, range.width());
        assertEquals(0, range.height());

        range = new MinMaxDouble(1, 2, 0, 10);
        assertEquals(1.0, range.width());
        assertEquals(10, range.height());

        range = new MinMaxDouble(-1, 1, -1, 1);
        assertEquals(2.0, range.width());
        assertEquals(2, range.height());
    }

    @Test
    void testIsLine() {
        assertTrue(MinMaxDouble.of(0, 0, 0, 0).isLineOrPoint());
        assertTrue(MinMaxDouble.of(0, 0, 1, 10).isLineOrPoint());
        assertTrue(MinMaxDouble.of(1, 10, 1, 1).isLineOrPoint());
        assertFalse(MinMaxDouble.of(1, 10, 1, 10).isLineOrPoint());
    }

    @Test
    void testContains() {
        checkRange(new MinMaxDouble(0, 0, 0, 0));
        checkRange(MinMaxDouble.of(10, 20));
        checkRange(MinMaxDouble.of(1));
    }

    private void checkRange(MinMaxDouble range) {
        double epsilon = 0.000001;
        assertTrue(range.contains(range.minX(), range.minY()));
        assertTrue(range.contains(range.minX(), range.maxY()));
        assertTrue(range.contains(range.maxX(), range.minY()));
        assertTrue(range.contains(range.maxX(), range.maxY()));

        assertFalse(range.contains(range.minX() - epsilon, range.minY()));
        assertFalse(range.contains(range.minX(), range.maxY() + epsilon));
        assertFalse(range.contains(range.maxX(), range.minY() - epsilon));
        assertFalse(range.contains(range.maxX(), range.maxY() + epsilon));
    }
}
