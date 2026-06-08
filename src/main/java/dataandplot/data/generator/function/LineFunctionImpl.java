package dataandplot.data.generator.function;

import dataandplot.config.DataLineConfig;
import dataandplot.config.FunctionConfig;
import dataandplot.data.generator.function.param.Parameter;

import java.util.function.DoubleUnaryOperator;


public record LineFunctionImpl(double a, double b) implements DoubleUnaryOperator {

    public LineFunctionImpl(DataLineConfig dataLineConfig) {
        this(dataLineConfig.functionConfig());
    }

    public LineFunctionImpl(FunctionConfig functionConfig) {
        this(functionConfig.getDoubleValueBy(Parameter.LINE_A),
                functionConfig.getDoubleValueBy(Parameter.LINE_B));
    }

    @Override
    public double applyAsDouble(double x) {
        return a * x + b;
    }

}
