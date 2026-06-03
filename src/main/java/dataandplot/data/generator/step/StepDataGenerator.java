package dataandplot.data.generator.step;

import dataandplot.data.generator.config.GeneratorInfo;

public interface StepDataGenerator {
    GeneratorInfo generatorInfo();
    double generate(int step);
}
