package dataandplot.config;

import dataandplot.data.generator.DataGeneratorType;
import dataandplot.plot.Insets;

import java.awt.*;
import java.util.Properties;

public record UIConfig(String name, Dimension dimension, Insets plotInsets) {

    public UIConfig(final Properties properties) {
        this(properties.getProperty("app.name"),
            new Dimension(Integer.parseInt(properties.getProperty("app.frame.width")),
                Integer.parseInt(properties.getProperty("app.frame.height"))),
                    new Insets(properties));
    }
}
