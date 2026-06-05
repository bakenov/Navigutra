package dataandplot.data.generator.step;

import dataandplot.config.DataConfig;
import dataandplot.model.data.index.IndexToXDouble;

import java.util.function.DoubleUnaryOperator;

public class StepDataGeneratorDoubleImpl implements StepDataGeneratorDouble {

    private final DoubleUnaryOperator function;
    private final DataConfig dataConfig;
//    private final IndexToXDouble indexToX;


    public StepDataGeneratorDoubleImpl(final DataConfig dataConfig, final DoubleUnaryOperator function) {
        if (dataConfig == null)
            throw new RuntimeException("GeneratorInfo is null.");
        this.dataConfig = dataConfig;
        this.function = function;
    }

//    @Override
//    public double generate(double x) {
//        return function.applyAsDouble(x);
//    }

    @Override
    public double generate(int index) {
        return 0;
    }
}
