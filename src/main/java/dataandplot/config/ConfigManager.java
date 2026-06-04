package dataandplot.config;

import dataandplot.plot.Insets;

import java.awt.*;
import java.io.File;
import java.util.Properties;

public interface ConfigManager {

    String APP_CONFIG_FILE_NAME = "configUi.properties";
    String APP_CONFIG_DIR_NAME = "dataConfig";

    // application configuration
    String getTitle();
    Dimension getFrameSize();
    Insets getInsets();
    // data configuration file
    File getConfigDir();
    void dataConfigFileChanged(File newDataConfigFile);
    Properties getDataConfiguration();
}
