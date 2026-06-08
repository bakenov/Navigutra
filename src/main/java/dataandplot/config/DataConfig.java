package dataandplot.config;

import java.util.List;

public interface DataConfig {

    String TITLE = "app.data.title";
    String SIZE = "app.data.size";
    String X_UNIT = "app.data.stepMultiplicator";

    String getTitle();
    int getSize();
    double getStepMultiplicator();
    List<DataLineConfig> getDataLineConfigs();
}
