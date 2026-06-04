package dataandplot.data.provider.builder;

import dataandplot.data.generator.config.GeneratorInfo;
import dataandplot.data.provider.DataProvider;

import java.util.Properties;

public interface DataProviderBuilder {
    void build(final GeneratorInfo generatorInfo, final Properties config);
}
