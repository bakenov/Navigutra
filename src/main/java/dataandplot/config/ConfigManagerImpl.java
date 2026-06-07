package dataandplot.config;


import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;

import static dataandplot.util.Utils.loadProperties;

public class ConfigManagerImpl implements ConfigManager {

    private final UIConfig uiConfig;
    private final File configDir;
    private DataConfig dataConfig;
    private String processedDataFile;

    public ConfigManagerImpl() {
        uiConfig = new UIConfig(loadProperties(APP_CONFIG_FILE_NAME));
        configDir = buildConfigDir();
    }

    public ConfigManagerImpl(ClassLoader classLoader) {
        uiConfig = new UIConfig(loadProperties(APP_CONFIG_FILE_NAME, classLoader));
        configDir = buildConfigDir();
    }

    @Override
    public UIConfig getUIConfig() {
        return uiConfig;
    }

    @Override
    public File getConfigDir() {
        return configDir;
    }

    @Override
    public boolean dataConfigFileChanged(File newDataConfigFile) {
        if (newDataConfigFile != null && (processedDataFile == null || ! processedDataFile.equals(newDataConfigFile.getAbsolutePath()))) {
            Properties dataProperties = loadProperties(newDataConfigFile);
            dataConfig = new DataConfigImpl(dataProperties);
            processedDataFile = newDataConfigFile.getAbsolutePath();
            //IO.println("ConfigManagerImpl.dataConfigFileChanged()   processedDataFile=" + processedDataFile);
            return true;
        }
        return false;
    }

    @Override
    public DataConfig getDataConfig() {
        return dataConfig;
    }

    private File buildConfigDir() {
        URL resourceUrl = getClass().getClassLoader().getResource(APP_CONFIG_DIR_NAME);
        if (resourceUrl != null) {
            try {
                return new File(resourceUrl.toURI());
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
        }
        throw new RuntimeException("No config dir in application's resource folder");
    }
}
