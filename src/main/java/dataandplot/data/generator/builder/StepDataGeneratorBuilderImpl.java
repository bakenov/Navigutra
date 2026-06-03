package dataandplot.data.generator.builder;

import dataandplot.data.generator.config.*;
import dataandplot.data.generator.step.*;

public class StepDataGeneratorBuilderImpl implements StepDataGeneratorBuilder {
    @Override
    public FunctionStepDataGenerator buildStepGenerator(final GeneratorInfo generatorInfo, FunctionInfo... functionInfos) {
        switch (generatorInfo.type()) {
            case SINE:
                if (functionInfos[0] instanceof SineFunctionInfo functionInfo) {
                    return new SineStepDataGenerator(functionInfo);
                }
                break;
            case LINE:
                if (functionInfos[0] instanceof LineFunctionInfo functionInfo) {
                    return new LinearStepDataGenerator(functionInfo);
                }
                break;
            case NOISE:
                if (functionInfos[0] instanceof NoiseFunctionInfo functionInfo) {
                    return new NoiseStepDataGenerator(functionInfo);
                }
                break;
            case SINE_NOISE:
                if (functionInfos.length == 2) {
                    if (functionInfos[0] instanceof SineFunctionInfo functionInfo) {
                        FunctionStepDataGenerator baseDataGenerator = new SineStepDataGenerator(functionInfo);
                        if (functionInfos[1] instanceof NoiseFunctionInfo functionInfo2) {
                            return new NoiseStepDataGenerator(functionInfo2, baseDataGenerator);
                        }
                    }
                }
                break;
            case LINE_NOISE:
                if (functionInfos.length == 2) {
                    if (functionInfos[0] instanceof LineFunctionInfo functionInfo) {
                        FunctionStepDataGenerator baseDataGenerator = new LinearStepDataGenerator(functionInfo);
                        if (functionInfos[1] instanceof NoiseFunctionInfo functionInfo2) {
                            return new NoiseStepDataGenerator(functionInfo2, baseDataGenerator);
                        }
                    }
                }
                break;
            case SINE_LINE:
                if (functionInfos.length == 2) {
                    if (functionInfos[0] instanceof SineFunctionInfo functionInfo) {
                        FunctionStepDataGenerator baseDataGenerator = new SineStepDataGenerator(functionInfo);
                        if (functionInfos[1] instanceof LineFunctionInfo functionInfo2) {
                            return new LinearStepDataGenerator(functionInfo2, baseDataGenerator);
                        }
                    }
                }
                break;
            case SINE_LINE_NOISE:
                if (functionInfos.length == 3) {
                    if (functionInfos[0] instanceof SineFunctionInfo functionInfo) {
                        FunctionStepDataGenerator baseDataGenerator = new SineStepDataGenerator(functionInfo);
                        if (functionInfos[1] instanceof LineFunctionInfo functionInfo2) {
                            FunctionStepDataGenerator baseDataGenerator2 = new LinearStepDataGenerator(functionInfo2, baseDataGenerator);
                            if (functionInfos[2] instanceof NoiseFunctionInfo functionInfo3) {
                                return new NoiseStepDataGenerator(functionInfo3, baseDataGenerator2);
                            }
                        }
                    }
                }
        }
        throw new RuntimeException("Unknown generator type:" + generatorInfo.type());
    }
}
