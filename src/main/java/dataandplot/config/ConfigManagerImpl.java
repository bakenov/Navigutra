package dataandplot.config;


import dataandplot.plot.Insets;

import java.awt.*;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;

import static dataandplot.util.Utils.loadProperties;

public class ConfigManagerImpl implements ConfigManager {

    private final Properties uiConfig;
    private Properties dataConfig;
    private final File configDir;

    public ConfigManagerImpl() {
        uiConfig = loadProperties(APP_CONFIG_FILE_NAME);
        configDir = buildConfigDir();
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
    public File getConfigDir() {
        return configDir;
    }

    @Override
    public void dataConfigFileChanged(File newDataConfigFile) {
        IO.println("ConfigManagerImpl.dataConfigFileChanged()   newDataConfigFile=" + newDataConfigFile);
        if (newDataConfigFile != null) {
            dataConfig = loadProperties(newDataConfigFile);
            IO.println("ConfigManagerImpl.dataConfigFileChanged()   dataConfig=" + dataConfig);
        }
    }

    @Override
    public Properties getDataConfiguration() {
        return dataConfig;
    }

    private File buildConfigDir() {
        URL resourceUrl = getClass().getClassLoader().getResource(APP_CONFIG_DIR_NAME);
        if (resourceUrl != null) {
            // Convert URL to a standard File object
            try {
                return new File(resourceUrl.toURI());
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
        }
        throw new RuntimeException("No config dir in application's resource folder");
    }
}
