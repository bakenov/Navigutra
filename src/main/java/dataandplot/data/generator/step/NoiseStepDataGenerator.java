package dataandplot.data.generator.step;

import dataandplot.data.generator.config.DataGeneratorType;
import dataandplot.data.generator.config.NoiseFunctionInfo;

import static dataandplot.data.generator.config.DataGeneratorType.*;

import java.util.Random;

public class NoiseStepDataGenerator implements FunctionStepDataGenerator {

    private final double noiseLevel;
    private final Random random = new Random();
    private final FunctionStepDataGenerator baseDataGenerator;

    public NoiseStepDataGenerator(final NoiseFunctionInfo functionInfo) {
        this(functionInfo, null);
    }

    public NoiseStepDataGenerator(final NoiseFunctionInfo functionInfo, final FunctionStepDataGenerator baseDataGenerator) {
        this.noiseLevel = functionInfo.noiseLevel();
        this.baseDataGenerator = baseDataGenerator;
    }

    @Override
    public double generate(double step) {
        return (baseDataGenerator == null ? 0.0 : baseDataGenerator.generate(step)) + random.nextDouble() * noiseLevel;
    }

    @Override
    public DataGeneratorType functionType() {
        if (baseDataGenerator == null) {
            return DataGeneratorType.NOISE;
        }
        DataGeneratorType baseType = baseDataGenerator.functionType();
        return switch (baseType) {
            case LINE -> LINE_NOISE;
            case SINE -> SINE_NOISE;
            case SINE_LINE -> SINE_LINE_NOISE;
            default -> UNKNOWN;
        };
    }
}
