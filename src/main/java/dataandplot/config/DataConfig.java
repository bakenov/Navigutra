package dataandplot.config;

import java.util.List;

public interface DataConfig {

    String getTitle();
    int getSize();
    double getStepMultiplicator();
    List<DataLineConfig> getDataLineConfigs();
}
