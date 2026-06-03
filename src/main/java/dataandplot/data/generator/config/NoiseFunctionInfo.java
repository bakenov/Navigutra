package dataandplot.data.generator.config;

import java.util.Properties;

public record NoiseFunctionInfo(double noiseLevel) implements FunctionInfo {

    public NoiseFunctionInfo(final Properties properties) {
        this(Double.parseDouble(properties.getProperty("app.data.noise.noiseLevel")));
    }

}