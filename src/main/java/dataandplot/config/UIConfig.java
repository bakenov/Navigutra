package dataandplot.config;

import dataandplot.plot.Insets;

import static dataandplot.config.ConfigManager.*;

import java.awt.*;
import java.util.Properties;

public record UIConfig(String name, Dimension dimension, Insets plotInsets, String dataConfigFileName) {

    public UIConfig(final Properties properties) {
        this(properties.getProperty(APP_NAME),
            new Dimension(Integer.parseInt(properties.getProperty(APP_FRAME_WIDTH)),
                Integer.parseInt(properties.getProperty(APP_FRAME_HEIGHT))),
                    new Insets(properties),
                        properties.getProperty(DATA_CONFIG_FILE));
    }
}
