package dataandplot.data.generator.step;

import dataandplot.data.generator.config.DataGeneratorType;
import dataandplot.data.generator.config.LineFunctionInfo;

import static dataandplot.data.generator.config.DataGeneratorType.*;

public class LinearStepDataGenerator implements FunctionStepDataGenerator {

    private double a;
    private double b;
    private final FunctionStepDataGenerator baseDataGenerator;

    public LinearStepDataGenerator(final LineFunctionInfo functionInfo) {
        this(functionInfo, null);
    }

    public LinearStepDataGenerator(final LineFunctionInfo functionInfo, final FunctionStepDataGenerator baseDataGenerator) {
        this.a = functionInfo.a();
        this.b = functionInfo.b();
        this.baseDataGenerator = baseDataGenerator;
    }

    private double callFunction(double step) {
        double value = a * step + b;
        IO.println("LinearStepDataGenerator.callFunction()     step=" + step + "    value=" + value);
        return value;
    }

    @Override
    public double generate(double step) {
        if (baseDataGenerator == null) {
            return callFunction(step);
        }
        return baseDataGenerator.generate(step) + callFunction(step);
    }

    @Override
    public DataGeneratorType functionType() {
        if (baseDataGenerator == null) {
            return LINE;
        }
        DataGeneratorType baseType = baseDataGenerator.functionType();
        if (baseType == SINE_LINE) {
            return SINE_LINE;
        }
        return UNKNOWN;
    }
}
