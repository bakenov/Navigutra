package dataandplot.config;

import java.io.File;

public interface ConfigManager {

    String APP_CONFIG_FILE_NAME = "configUi.properties";
    String APP_CONFIG_DIR_NAME = "dataConfig";

    File getConfigDir();
    boolean dataConfigFileChanged(File newDataConfigFile);
    DataConfig getDataConfig();
    UIConfig getUIConfig();
}
