package dataandplot.data.generator.builder;

import dataandplot.config.DataLineConfig;
import dataandplot.data.generator.dataline.DataLineGenerator;

public interface DataLineGeneratorBuilder {
    DataLineGenerator getDataLineGenerator(final DataLineConfig dataLineConfig);
}
