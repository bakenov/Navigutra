package dataandplot.data.model.data;

import dataandplot.data.dataset.DataSetImpl;
import dataandplot.data.range.DataBounds;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DataSetDoubleImplTest {

    @Test
    void testConstractorZeroSize() {
        DataSetImpl dataSet = new DataSetImpl(0);
        assertEquals(0, dataSet.getDataLength());
        assertNull(dataSet.getDataRange());
        assertThrows(RuntimeException.class, () -> {
            dataSet.setData(1., 1.);
        });
        assertThrows(RuntimeException.class, () -> {
            dataSet.getDataAt(0);
        });
    }

    @Test
    void testConstractorOneSize() {
        DataSetImpl dataSet = new DataSetImpl(1);
        assertEquals(1, dataSet.getDataLength());
        dataSet.setData(1., 2.);
        assertNull(dataSet.getDataRange());
        dataSet.endOfData();
        DataBounds range = dataSet.getDataRange();
        assertEquals(new DataBounds(1., 1., 2., 2.), range);

        double[] point = dataSet.getDataAt(0);
        assertEquals(1., point[0]);
        assertEquals(2., point[1]);

        assertThrows(RuntimeException.class, () -> {
            dataSet.setData(2., 3.);
        });
        assertThrows(RuntimeException.class, () -> {
            dataSet.getDataAt(1);
        });
    }

}
