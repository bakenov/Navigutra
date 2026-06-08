package dataandplot.data.model.data.generator.builder;

import dataandplot.config.DataConfig;
import dataandplot.data.generator.dataline.builder.DataLineGeneratorBuilderImpl;
import dataandplot.data.generator.dataline.DataLineGenerator;
import dataandplot.data.generator.dataline.DataLineGeneratorImpl;
import dataandplot.data.generator.function.LineFunctionImpl;
import dataandplot.data.model.data.generator.BaseGeneratorTest;
import dataandplot.data.generator.index.IndexToXDouble;
import dataandplot.data.generator.index.IndexToXDoubleImpl;

import org.junit.jupiter.api.Test;

import java.util.function.DoubleUnaryOperator;

import static org.junit.jupiter.api.Assertions.*;

public class DataLineGeneratorBuilderImplTest extends BaseGeneratorTest {

    @Test
    void testBuilder1() {
        DataConfig dataConfig = buildDataConfig();
        DataLineGeneratorBuilderImpl builder = new DataLineGeneratorBuilderImpl();
        builder.setDataConfig(dataConfig);
        DataLineGenerator gen = builder.getDataLineGenerator(buildDataLineConfig());
        assertInstanceOf(DataLineGeneratorImpl.class, gen);
        DataLineGeneratorImpl generator = (DataLineGeneratorImpl) gen;
        assertEquals("testName", generator.dataLineName());

        DoubleUnaryOperator fun = generator.function();
        assertInstanceOf(LineFunctionImpl.class, fun);
        LineFunctionImpl function = (LineFunctionImpl) fun;
        assertEquals(2.5, function.a());
        assertEquals(1, function.b());

        IndexToXDouble to = generator.indexToXDouble();
        assertInstanceOf(IndexToXDoubleImpl.class, to);
        IndexToXDoubleImpl indexToXDouble = (IndexToXDoubleImpl) to;
        assertEquals(2.0, indexToXDouble.xunit());
    }
}
