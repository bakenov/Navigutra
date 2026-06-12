package dataandplot.data.generator.function;

import dataandplot.config.DataLineConfig;
import dataandplot.config.FunctionConfig;
import dataandplot.data.generator.function.param.Parameter;
import dataandplot.data.generator.index.IndexToXDouble;
import dataandplot.data.generator.index.IndexToXDoubleImpl;

import java.util.function.DoubleUnaryOperator;


public record SineFunctionImpl(double amplitude, double initInRadian, IndexToXDouble indexToXDouble) implements DataFunction {

    public SineFunctionImpl(DataLineConfig dataLineConfig) {
        this(dataLineConfig.functionConfig());
    }

    public SineFunctionImpl(FunctionConfig functionConfig) {
        this(functionConfig.getDoubleValueBy(Parameter.SIN_AMPLITUDE),
                functionConfig.getDoubleValueBy(Parameter.SIN_INIT_RADIAN),
                new IndexToXDoubleImpl(functionConfig.getDoubleValueBy(Parameter.SIN_STEP_VALUE)));
    }

    @Override
    public double applyAsDouble(double radian) {
        return amplitude * Math.sin((initInRadian + radian) * Math.PI / 180.0);
    }

}
