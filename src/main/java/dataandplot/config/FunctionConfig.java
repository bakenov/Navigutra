package dataandplot.config;

import dataandplot.data.generator.function.param.Parameter;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class FunctionConfig {

    private final Map<Parameter, String> parameters = new HashMap<>(8);

    public FunctionConfig processProperties(int lineIndex, final Properties properties) {
        parameters.clear();
        EnumSet.allOf(Parameter.class)
                .forEach(param -> {
                    String key = buildKey(lineIndex, param.getPropertyName());
                    if (properties.containsKey(key)) {
                        String value = properties.getProperty(key);
                        set(param, value);
                    }
                });
        return this;
    }

    // mostly for tests
    public void set(Parameter param, String value) {
        parameters.put(param, value);
    }

    private String buildKey(int lineIndex, String property) {
        return "app.data." + lineIndex + "." + property;
    }

    public boolean containsKey(Parameter param) {
        return parameters.containsKey(param);
    }

    public double getDoubleValueBy(Parameter param) {
        if (parameters.containsKey(param)) {
            String stringValue = parameters.get(param);
            return param.convertToDouble(stringValue);
        }
        return Double.MAX_VALUE;
    }

    public int getIntValueBy(Parameter param) {
        if (parameters.containsKey(param)) {
            String stringValue = parameters.get(param);
            return param.convertToInt(stringValue);
        }
        return Integer.MAX_VALUE;
    }

    public String getValueBy(Parameter param) {
        if (parameters.containsKey(param)) {
            return parameters.get(param);
        }
        return null;
    }

    public String toString() {
        return "FunctionConfig(" + parameters + ")";
    }
}
