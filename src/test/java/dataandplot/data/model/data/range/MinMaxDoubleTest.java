package dataandplot.data.model.data.range;

import dataandplot.model.data.range.MinMaxDouble;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
