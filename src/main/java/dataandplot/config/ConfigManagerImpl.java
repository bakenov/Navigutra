package dataandplot.config;


import dataandplot.plot.Insets;

import java.awt.*;
import java.util.Properties;

import static dataandplot.util.Utils.loadProperties;

public class ConfigManagerImpl implements ConfigManager {

    private final Properties uiConfig;
    private Properties dataConfig;

    public ConfigManagerImpl() {
        uiConfig = loadProperties("configUi.properties");
    }

    @Override
    public String getTitle() {
        return uiConfig.getProperty("app.name");
    }

    @Override
    public Dimension getFrameSize() {
        int width = Integer.parseInt(uiConfig.getProperty("app.frame.width"));
        int height = Integer.parseInt(uiConfig.getProperty("app.frame.height"));
        return new Dimension(width, height);
    }

    @Override
    public Insets getInsets() {
        return new Insets(Integer.parseInt(uiConfig.getProperty("app.plot.insets.left")),
                Integer.parseInt(uiConfig.getProperty("app.plot.insets.right")),
                Integer.parseInt(uiConfig.getProperty("app.plot.insets.top")),
                Integer.parseInt(uiConfig.getProperty("app.plot.insets.bottom")));
    }

    @Override
    public boolean setDataConfigFile(String dataConfigFile) {
        if (dataConfigFile == null) {
            return false;
        }
        dataConfig = loadProperties(dataConfigFile);
        return dataConfig != null;
    }

    @Override
    public Properties getDataConfig() {
        return dataConfig;
    }
}
