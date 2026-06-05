package dataandplot.data.generator;

import dataandplot.config.DataConfig;
import dataandplot.util.DataRangeFloat;

public interface DataGenerator {
    DataConfig generatorInfo();
    void generateData();
    DataRangeFloat getDataRange();
}
