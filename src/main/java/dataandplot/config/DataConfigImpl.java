package dataandplot.config;

import dataandplot.data.generator.DataGeneratorType;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DataConfigImpl implements DataConfig {

    private final String title;
    private final int size;
    private final double stepMultiplicator;
    private final List<DataLineConfig> dataLineConfigs;

    public DataConfigImpl(final Properties properties) {
        this.title = properties.getProperty("app.data.title");
        this.size = Integer.parseInt(properties.getProperty("app.data.size"));
        this.stepMultiplicator = Double.parseDouble(properties.getProperty("app.data.stepMultiplicator"));
        dataLineConfigs = new ArrayList<>();
        processDataLines(properties);
    }

    private void processDataLines(final Properties properties) {
        int lineIndex = 1;
        String key = buildKey(lineIndex, "name");
        while (properties.containsKey(key)) {
            String name = properties.getProperty(key);
            key = buildKey(lineIndex, "dataType");
            key = buildKey(lineIndex, "funType");
            DataGeneratorType type = DataGeneratorType.valueOf(properties.getProperty(key));
            FunctionConfig config = new FunctionConfig().processProperties(lineIndex, properties);
            dataLineConfigs.add(new DataLineConfig(name, type, config));
            key = buildKey(++lineIndex, "name");
        }
    }

    private String buildKey(int lineIndex, String property) {
        return "app.data." + lineIndex + "." + property;
    }

    public String getTitle() {
        return title;
    }

    public int getSize() {
        return size;
    }

    public double getStepMultiplicator() {
        return stepMultiplicator;
    }

    @Override
    public List<DataLineConfig> getDataLineConfigs() {
        return dataLineConfigs;
    }

    public String toString() {
        return "DataConfig(" + title + "|" + size + "|" + stepMultiplicator + "|" + dataLineConfigs + ")";
    }
}
