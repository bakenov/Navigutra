package dataandplot.data.generator.config;

import java.util.Properties;

public record GeneratorInfo(int size, double stepMultiplicator, DataGeneratorType type) {

    public GeneratorInfo(final Properties properties) {
        this(Integer.parseInt(properties.getProperty("app.data.size")),
                Double.parseDouble(properties.getProperty("app.data.stepMultiplicator")),
                DataGeneratorType.valueOf(properties.getProperty("app.data.type")));
    }
}
