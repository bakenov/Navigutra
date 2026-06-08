package dataandplot.data.model.data.range;

import dataandplot.data.range.DataBounds;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MinMaxDoubleTest {

    @Test
    void test() {
        DataBounds range = new DataBounds(0, 0, 0, 0);
        assertEquals(0, range.width());
        assertEquals(0, range.height());

        range = new DataBounds(1, 2, 0, 10);
        assertEquals(1.0, range.width());
        assertEquals(10, range.height());

        range = new DataBounds(-1, 1, -1, 1);
        assertEquals(2.0, range.width());
        assertEquals(2, range.height());
    }

    @Test
    void testIsLine() {
        assertTrue(DataBounds.of(0, 0, 0, 0).isLineOrPoint());
        assertTrue(DataBounds.of(0, 0, 1, 10).isLineOrPoint());
        assertTrue(DataBounds.of(1, 10, 1, 1).isLineOrPoint());
        assertFalse(DataBounds.of(1, 10, 1, 10).isLineOrPoint());
    }

    @Test
    void testContains() {
        checkRange(new DataBounds(0, 0, 0, 0));
        checkRange(DataBounds.of(10, 20));
        checkRange(DataBounds.of(1));
    }

    private void checkRange(DataBounds range) {
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
