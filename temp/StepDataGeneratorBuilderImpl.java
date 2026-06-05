package dataandplot.data.generator.builder;

import dataandplot.config.DataConfig;
import dataandplot.data.generator.step.*;

public class StepDataGeneratorBuilderImpl implements StepDataGeneratorBuilder {

    private final DataConfig dataConfig;

    public StepDataGeneratorBuilderImpl(DataConfig dataConfig) {
        this.dataConfig = dataConfig;
    }

    @Override
    public StepDataGenerator buildStepGenerator(String dataName) {
        return null;
    }

}
