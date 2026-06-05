package dataandplot.data.generator.builder;

import dataandplot.data.generator.step.StepDataGenerator;

public interface StepDataGeneratorBuilder {

    StepDataGenerator buildStepGenerator(String dataName);
}
