package dataandplot.plot;

import dataandplot.config.ConfigManager;
import dataandplot.data.generator.DataGeneratorType;

import java.util.Properties;

public record Insets(int left, int right, int top, int bottom) {

    public Insets() {
        this(50, 20, 20, 40);
    }

    public Insets(final Properties properties) {
        this(Integer.parseInt(properties.getProperty("app.plot.insets.left")),
                Integer.parseInt(properties.getProperty("app.plot.insets.right")),
                    Integer.parseInt(properties.getProperty("app.plot.insets.top")),
                        Integer.parseInt(properties.getProperty("app.plot.insets.bottom")));
    }

    public int getWidth() {
        return left + right;
    }

    public int getHeight() {
        return top + bottom;
    }
}
