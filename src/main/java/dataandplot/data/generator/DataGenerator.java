package dataandplot.data.generator;

import dataandplot.data.generator.config.GeneratorInfo;
import dataandplot.util.DataRangeFloat;

public interface DataGenerator {
    GeneratorInfo generatorInfo();
    void generateData();
    DataRangeFloat getDataRange();
}
