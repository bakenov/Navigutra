package dataandplot.data.generator.builder;

import dataandplot.data.generator.config.DataGeneratorType;
import dataandplot.data.generator.config.FunctionInfo;
import dataandplot.data.generator.config.GeneratorInfo;
import dataandplot.data.generator.config.SineFunctionInfo;
import dataandplot.data.generator.step.FunctionStepDataGenerator;
import dataandplot.data.generator.step.SineStepDataGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StepDataGeneratorBuilderImplTest {

    @Test
    void testLine_1StepNoB() {
        int size = 10;
        GeneratorInfo dataInfo = new GeneratorInfo(size, 1.0, DataGeneratorType.SINE);
        FunctionInfo functionInfo = new SineFunctionInfo(size, 1, size);
        StepDataGeneratorBuilder builder = new StepDataGeneratorBuilderImpl();
        FunctionStepDataGenerator dataGenerator = builder.buildStepGenerator(dataInfo, functionInfo);
        DataGeneratorType tytpe = dataGenerator.functionType();
        assertEquals(DataGeneratorType.SINE, tytpe);
        assertTrue(dataGenerator instanceof SineStepDataGenerator);
    }
}
