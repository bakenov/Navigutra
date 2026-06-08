package dataandplot.data.generator.dataline.builder;

import dataandplot.config.DataConfig;
import dataandplot.config.DataLineConfig;
import dataandplot.data.generator.dataline.DataLineGenerator;
import dataandplot.data.generator.dataline.DataLineGeneratorImpl;
import dataandplot.data.generator.function.LineFunctionImpl;
import dataandplot.data.generator.index.IndexToXDouble;
import dataandplot.data.generator.index.IndexToXDoubleImpl;

import java.util.HashMap;
import java.util.Map;

public class DataLineGeneratorBuilderImpl implements DataLineGeneratorBuilder {

    private final Map<String, DataLineGenerator> dataGeneratorMap;
    private DataConfig dataConfig;
    private IndexToXDouble indexToXDouble;

    public DataLineGeneratorBuilderImpl() {
        dataGeneratorMap = new HashMap<>();
    }

    public void setDataConfig(final DataConfig dataConfig) {
        this.dataConfig = dataConfig;
        this.indexToXDouble = new IndexToXDoubleImpl(dataConfig.getStepMultiplicator());
    }

    private DataLineGenerator buildDataLineGenerator(DataLineConfig dataLineConfig) {
        LineFunctionImpl functionDouble = new LineFunctionImpl(dataLineConfig);
        return new DataLineGeneratorImpl(dataConfig, dataLineConfig, functionDouble, indexToXDouble);
    }

    @Override
    public DataLineGenerator getDataLineGenerator(final DataLineConfig dataLineConfig) {
        String name = dataLineConfig.name();
        if (!dataGeneratorMap.containsKey(name)) {
            DataLineGenerator dataLineGenerator = buildDataLineGenerator(dataLineConfig);
            dataGeneratorMap.put(name, dataLineGenerator);
        }
        return dataGeneratorMap.get(name);
    }
}
