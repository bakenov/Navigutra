package dataandplot.data.generator.config.builder;

import dataandplot.data.generator.config.*;

import java.util.Properties;

public class FunctionInfoBuilder {

    public FunctionInfo buildFunctionInfo(final DataGeneratorType type, final Properties properties) {
        return switch (type) {
            case SINE -> new SineFunctionInfo(properties);
            case LINE -> new LineFunctionInfo(properties);
            case NOISE -> new NoiseFunctionInfo(properties);
            default -> throw new RuntimeException("Unknown generator type:" + type);
        };
    }
}
