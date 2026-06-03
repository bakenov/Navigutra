package dataandplot.data.generator.config;

import java.util.Properties;

public record SineFunctionInfo(double amplitude, int thetaInit, int thetaStep) implements FunctionInfo {

    public SineFunctionInfo(final Properties properties) {
        this(Double.parseDouble(properties.getProperty("app.data.sine.amplitude")),
                Integer.parseInt(properties.getProperty("app.data.sine.thetaInit")),
                        Integer.parseInt(properties.getProperty("app.data.sine.thetaStep")));
    }

}