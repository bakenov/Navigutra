package dataandplot.data.generator.step;

import dataandplot.data.generator.config.DataGeneratorType;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleUnaryOperator;

public interface FunctionStepDataGenerator {
    double generate(double step);
    DataGeneratorType functionType();
}
