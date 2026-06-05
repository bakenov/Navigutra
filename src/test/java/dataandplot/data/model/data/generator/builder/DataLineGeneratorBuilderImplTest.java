package dataandplot.data.model.data.generator.builder;

import dataandplot.config.DataConfig;
import dataandplot.data.generator.builder.DataLineGeneratorBuilderImpl;
import dataandplot.data.generator.dataline.DataLineGenerator;
import dataandplot.data.generator.dataline.DataLineGeneratorDouble;
import dataandplot.data.generator.function.LineFunctionDoubleImpl;
import dataandplot.data.model.data.generator.BaseGeneratorTest;
import dataandplot.model.data.DataSet;
import dataandplot.model.data.DataSetDouble;
import dataandplot.model.data.index.IndexToXDouble;
import dataandplot.model.data.index.IndexToXDoubleImpl;
import dataandplot.model.data.range.MinMaxDouble;
import org.junit.jupiter.api.Test;

import java.util.function.DoubleUnaryOperator;

import static org.junit.jupiter.api.Assertions.*;

public class DataLineGeneratorBuilderImplTest extends BaseGeneratorTest {

    @Test
    void testBuilder1() {
        DataConfig dataConfig = buildDataConfig();
        DataLineGeneratorBuilderImpl builder = new DataLineGeneratorBuilderImpl(dataConfig);
        DataLineGenerator gen = builder.buildDataLineGenerator(buildDataLineConfig());
        assertInstanceOf(DataLineGeneratorDouble.class, gen);
        DataLineGeneratorDouble generator = (DataLineGeneratorDouble) gen;
        assertEquals("testName", generator.dataLineName());

        DoubleUnaryOperator fun = generator.function();
        assertInstanceOf(LineFunctionDoubleImpl.class, fun);
        LineFunctionDoubleImpl function = (LineFunctionDoubleImpl) fun;
        assertEquals(2.5, function.a());
        assertEquals(1, function.b());

        IndexToXDouble to = generator.indexToXDouble();
        assertInstanceOf(IndexToXDoubleImpl.class, to);
        IndexToXDoubleImpl indexToXDouble = (IndexToXDoubleImpl) to;
        assertEquals(2.0, indexToXDouble.xunit());

        DataSet set = generator.dataSet();
        assertInstanceOf(DataSetDouble.class, set);
        DataSetDouble dataSetDouble = (DataSetDouble) set;
        MinMaxDouble range = dataSetDouble.getDataRange();
        assertNull(range);
//        assertEquals(Double.MIN_VALUE, range.minX());
//        assertEquals(Double.MAX_VALUE, range.maxX());
//        assertEquals(Double.MIN_VALUE, range.minY());
//        assertEquals(Double.MIN_VALUE, range.maxY());
    }
}
