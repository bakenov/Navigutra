package dataandplot.data.generator.config;

import java.util.Properties;

public record LineFunctionInfo(double a, double b) implements FunctionInfo {

    public LineFunctionInfo(final Properties properties) {
        this(Double.parseDouble(properties.getProperty("app.data.line.a")),
                Double.parseDouble(properties.getProperty("app.data.line.b")));
    }
}
