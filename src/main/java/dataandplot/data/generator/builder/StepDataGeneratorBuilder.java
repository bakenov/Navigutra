package dataandplot.data.generator.builder;

import dataandplot.data.generator.config.FunctionInfo;
import dataandplot.data.generator.config.GeneratorInfo;
import dataandplot.data.generator.step.FunctionStepDataGenerator;

public interface StepDataGeneratorBuilder {

    FunctionStepDataGenerator buildStepGenerator(final GeneratorInfo generatorInfo, FunctionInfo... functionInfo);
}
