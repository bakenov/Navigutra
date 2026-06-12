package dataandplot.config;

import java.util.List;

public interface DataConfig {

    String TITLE = "app.data.title";
    String SIZE = "app.data.size";

    String getTitle();
    int getSize();
    List<DataLineConfig> getDataLineConfigs();
}
