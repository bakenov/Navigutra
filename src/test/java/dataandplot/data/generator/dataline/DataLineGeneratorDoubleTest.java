package dataandplot.data.generator.dataline;

import dataandplot.config.DataConfig;
import dataandplot.config.DataLineConfig;
import dataandplot.data.generator.function.LineFunctionDoubleImpl;
import dataandplot.data.model.data.generator.BaseGeneratorTest;
import dataandplot.model.data.DataSetDoubleImpl;
import dataandplot.model.data.index.IndexToXDouble;
import dataandplot.model.data.index.IndexToXDoubleImpl;
import dataandplot.model.data.range.MinMaxDouble;
import org.junit.jupiter.api.Test;

import java.util.function.DoubleUnaryOperator;

import static org.junit.jupiter.api.Assertions.*;

public class DataLineGeneratorDoubleTest extends BaseGeneratorTest {

    @Test
    void test1() {
        DataConfig dataConfig = buildDataConfig();

        DataLineConfig dataLineConfig = buildDataLineConfig();
        DoubleUnaryOperator function = new LineFunctionDoubleImpl(dataLineConfig);
        IndexToXDouble indexToXDouble = new IndexToXDoubleImpl(dataConfig.getStepMultiplicator());

        DataLineGeneratorDouble generator = new DataLineGeneratorDouble(dataConfig,
                dataLineConfig, function, indexToXDouble);

        DataSetDoubleImpl dataSetDouble = new DataSetDoubleImpl(dataConfig.getSize());
        MinMaxDouble range = dataSetDouble.getDataRange();
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
