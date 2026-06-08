package dataandplot.data.generator.dataline.builder;

import dataandplot.config.DataConfig;
import dataandplot.config.DataLineConfig;
import dataandplot.data.generator.dataline.DataLineGenerator;

public interface DataLineGeneratorBuilder {
    DataLineGenerator getDataLineGenerator(final DataLineConfig dataLineConfig);
    void setDataConfig(final DataConfig dataConfig);
}
