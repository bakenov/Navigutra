package dataandplot.config;

import java.io.File;

public interface ConfigManager {

    String APP_CONFIG_FILE_NAME = "configUi.properties";
    String APP_CONFIG_DIR_NAME = "dataConfig";
    String APP_NAME = "app.name";
    String APP_FRAME_WIDTH = "app.frame.width";
    String APP_FRAME_HEIGHT = "app.frame.height";
    String DATA_CONFIG_FILE = "app.data.config.file";

    File getConfigDir();
    boolean dataConfigFileChanged(File newDataConfigFile);
    DataConfig getDataConfig();
    UIConfig getUIConfig();
}
