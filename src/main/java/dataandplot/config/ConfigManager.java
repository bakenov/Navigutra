package dataandplot.config;

import dataandplot.plot.Insets;

import java.awt.*;
import java.util.Properties;

public interface ConfigManager {

    String getTitle();
    Dimension getFrameSize();
    Insets getInsets();
    boolean setDataConfigFile(String dataConfigFile);
    Properties getDataConfig();
}
