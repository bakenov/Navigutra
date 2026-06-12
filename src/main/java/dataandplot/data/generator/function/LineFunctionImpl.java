package dataandplot.data.generator.function;

import dataandplot.config.DataLineConfig;
import dataandplot.config.FunctionConfig;
import dataandplot.data.generator.function.param.Parameter;
import dataandplot.data.generator.index.IndexToXDouble;
import dataandplot.data.generator.index.IndexToXDoubleImpl;

import java.util.function.DoubleUnaryOperator;


public record LineFunctionImpl(double a, double b, IndexToXDouble indexToXDouble) implements DataFunction {

    public LineFunctionImpl(DataLineConfig dataLineConfig) {
        this(dataLineConfig.functionConfig());
    }

    public LineFunctionImpl(FunctionConfig functionConfig) {
        this(functionConfig.getDoubleValueBy(Parameter.LINE_A),
                functionConfig.getDoubleValueBy(Parameter.LINE_B),
                    new IndexToXDoubleImpl(functionConfig.getDoubleValueBy(Parameter.LINE_STEP_VALUE)));
    }

    @Override
    public double applyAsDouble(double x) {
        return a * x + b;
    }

}
