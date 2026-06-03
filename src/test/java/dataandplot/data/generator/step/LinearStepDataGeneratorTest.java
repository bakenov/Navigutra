package dataandplot.data.generator.step;

import dataandplot.data.generator.config.LineFunctionInfo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LinearStepDataGeneratorTest {

    @Test
    void testLine_1StepNoB() {
        double b = 0.0;
        LineFunctionInfo lineInfo = new LineFunctionInfo(1., b);
        FunctionStepDataGenerator generator = new LinearStepDataGenerator(lineInfo);
        double result = generator.generate(0);
        assertEquals(b, result);
    }

    @Test
    void testLine_1Step() {
        double b = 1.0;
        LineFunctionInfo lineInfo = new LineFunctionInfo(1, b);
        FunctionStepDataGenerator generator = new LinearStepDataGenerator(lineInfo);
        double result = generator.generate(0);
        assertEquals(b, result);
    }

    @Test
    void testLine_2StepZeroMultiplicator() {
        double b = 0.0;
        LineFunctionInfo lineInfo = new LineFunctionInfo(2, b);
        FunctionStepDataGenerator generator = new LinearStepDataGenerator(lineInfo);
        double result = generator.generate(0);
        assertEquals(0., result);
        result = generator.generate(1);
        assertEquals(2, result);
    }

    @Test
    void testLine_2StepZeroMultiplicator2() {
        double b = 1.0;
        LineFunctionInfo lineInfo = new LineFunctionInfo(2, b);
        FunctionStepDataGenerator generator = new LinearStepDataGenerator(lineInfo);
        double result = generator.generate(0);
        assertEquals(b, result);
        result = generator.generate(1);
        assertEquals(3.0, result);
    }

    @Test
    void testLine_2StepOneMultiplicator() {
        double a = 1.0;
        double b = 1.0;
        LineFunctionInfo lineInfo = new LineFunctionInfo(a, b);
        FunctionStepDataGenerator generator = new LinearStepDataGenerator(lineInfo);
        double result = generator.generate(0);
        assertEquals(b, result);
        result = generator.generate(1);
        assertEquals(2.0, result);
    }

    @Test
    void testLine_2StepOneMultiplicator2() {
        double a = 2.3;
        double b = 1.5;
        LineFunctionInfo lineInfo = new LineFunctionInfo(a, b);
        FunctionStepDataGenerator generator = new LinearStepDataGenerator(lineInfo);
        double result = generator.generate(0);
        assertEquals(b, result);
        result = generator.generate(1);
        assertEquals(3.8, result);
    }
}
