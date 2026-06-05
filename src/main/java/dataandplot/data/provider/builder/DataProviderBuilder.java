package dataandplot.data.provider.builder;

import dataandplot.config.DataConfig;

import java.util.Properties;

public interface DataProviderBuilder {
    void build(final DataConfig dataConfig, final Properties config);
}
