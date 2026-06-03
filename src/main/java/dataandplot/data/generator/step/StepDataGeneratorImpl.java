package dataandplot.data.generator.step;

import dataandplot.data.generator.config.GeneratorInfo;

public class StepDataGeneratorImpl implements StepDataGenerator {

    private final FunctionStepDataGenerator dataGenerator;
    private final GeneratorInfo generatorInfo;

    public StepDataGeneratorImpl(final GeneratorInfo generatorInfo, final FunctionStepDataGenerator dataGenerator) {
        if (generatorInfo == null)
            throw new RuntimeException("GeneratorInfo is null.");
        this.generatorInfo = generatorInfo;
        this.dataGenerator = dataGenerator;
    }

    @Override
    public GeneratorInfo generatorInfo() {
        return generatorInfo;
    }

    private double updateStep(int step) {
        return generatorInfo.stepMultiplicator() * step;
    }

    @Override
    public double generate(int step) {
        return dataGenerator.generate(updateStep(step));
    }
}
