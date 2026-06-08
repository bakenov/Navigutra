package dataandplot.data.model.data.generator.function;

import dataandplot.config.DataLineConfig;
import dataandplot.data.generator.function.LineFunctionImpl;
import dataandplot.data.model.data.generator.BaseGeneratorTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LineFunctionDoubleImplTest extends BaseGeneratorTest {

    @Test
    void test() {
        // build data config
        DataLineConfig dataLineConfig = buildDataLineConfig();
        LineFunctionImpl functionDouble = new LineFunctionImpl(dataLineConfig);
        assertEquals(2.5, functionDouble.a());
        assertEquals(1.0, functionDouble.b());

        double y = functionDouble.applyAsDouble(0.0);
        assertEquals(1.0, y);
        y = functionDouble.applyAsDouble(1.0);
        assertEquals(3.5, y);
        y = functionDouble.applyAsDouble(2.0);
        assertEquals(6.0, y);
    }
}
