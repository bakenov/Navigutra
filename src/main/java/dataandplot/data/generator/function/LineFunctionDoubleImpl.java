package dataandplot.data.generator.function;

import dataandplot.config.DataLineConfig;
import dataandplot.config.FunctionConfig;
import dataandplot.data.generator.function.param.Parameter;


public record LineFunctionDoubleImpl(double a, double b) implements LineFunctionDouble {

    public LineFunctionDoubleImpl(DataLineConfig dataLineConfig) {
        this(dataLineConfig.functionConfig());
    }

    public LineFunctionDoubleImpl(FunctionConfig functionConfig) {
        this(functionConfig.getDoubleValueBy(Parameter.LINE_A),
                functionConfig.getDoubleValueBy(Parameter.LINE_B));
    }

    @Override
    public double applyAsDouble(double x) {
        return a * x + b;
    }

}
