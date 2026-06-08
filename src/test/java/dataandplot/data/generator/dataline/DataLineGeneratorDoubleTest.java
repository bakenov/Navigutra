package dataandplot.data.generator.dataline;

import dataandplot.config.DataConfig;
import dataandplot.config.DataLineConfig;
import dataandplot.data.generator.function.LineFunctionImpl;
import dataandplot.data.model.data.generator.BaseGeneratorTest;
import dataandplot.data.dataset.DataSetImpl;
import dataandplot.data.generator.index.IndexToXDouble;
import dataandplot.data.generator.index.IndexToXDoubleImpl;
import dataandplot.data.range.DataBounds;
import org.junit.jupiter.api.Test;

import java.util.function.DoubleUnaryOperator;

import static org.junit.jupiter.api.Assertions.*;

public class DataLineGeneratorDoubleTest extends BaseGeneratorTest {

    @Test
    void test1() {
        DataConfig dataConfig = buildDataConfig();

        DataLineConfig dataLineConfig = buildDataLineConfig();
        DoubleUnaryOperator function = new LineFunctionImpl(dataLineConfig);
        IndexToXDouble indexToXDouble = new IndexToXDoubleImpl(dataConfig.getStepMultiplicator());

        DataLineGeneratorImpl generator = new DataLineGeneratorImpl(dataConfig,
                dataLineConfig, function, indexToXDouble);

        DataSetImpl dataSetDouble = new DataSetImpl(dataConfig.getSize());
        DataBounds range = dataSetDouble.getDataRange();
        assertNull(range);

        generator.populateDataSet(dataSetDouble);

        range = dataSetDouble.getDataRange();
        assertEquals(0, range.minX());
        assertEquals(200, range.maxX());
        assertEquals(1, range.minY());
        assertEquals(501, range.maxY());

        int size = dataSetDouble.getDataLength();
        assertEquals(dataConfig.getSize(), size);

        double[] point;
        for (int i = 0; i < size; i++) {
            double x = indexToXDouble.toXbyIndex(i);
            double y = function.applyAsDouble(x);
            point = dataSetDouble.getDataAt(i);
            assertEquals(x, point[0]);
            assertEquals(y, point[1]);
        }

    }
}
