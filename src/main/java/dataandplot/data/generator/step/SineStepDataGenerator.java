package dataandplot.data.generator.step;

import dataandplot.data.generator.config.DataGeneratorType;
import dataandplot.data.generator.config.SineFunctionInfo;

import static dataandplot.data.generator.config.DataGeneratorType.*;

public class SineStepDataGenerator implements FunctionStepDataGenerator {

    private final double amplitude; // Max height of sine wave
    private final int thetaInit;
    private final int thetaStep;
    private double theta;

    public SineStepDataGenerator(final SineFunctionInfo functionInfo) {
        this.amplitude = functionInfo.amplitude();
        this.thetaInit = functionInfo.thetaInit();
        this.thetaStep = functionInfo.thetaStep();
        theta = thetaInit;
    }

    @Override
    public double generate(double step) {
        // Calculate standard sine value
        theta = thetaInit + step * thetaStep;
        return amplitude * Math.sin(theta * Math.PI / 180.0);
    }

    @Override
    public DataGeneratorType functionType() {
        return SINE;
    }
}
