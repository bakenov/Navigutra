package dataandplot.data.generator.builder;

import dataandplot.config.DataConfig;
import dataandplot.config.DataLineConfig;
import dataandplot.data.generator.dataline.DataLineGenerator;
import dataandplot.data.generator.dataline.DataLineGeneratorDouble;
import dataandplot.data.generator.function.LineFunctionDoubleImpl;
import dataandplot.data.generator.index.IndexToXDouble;
import dataandplot.data.generator.index.IndexToXDoubleImpl;

import java.util.HashMap;
import java.util.Map;

public class DataLineGeneratorBuilderImpl implements DataLineGeneratorBuilder {

    private final DataConfig dataConfig;
    private final IndexToXDouble indexToXDouble;
    private final Map<String, DataLineGenerator> dataGeneratorMap;

    public DataLineGeneratorBuilderImpl(DataConfig dataConfig) {
        this.dataConfig = dataConfig;
        indexToXDouble = new IndexToXDoubleImpl(dataConfig.getStepMultiplicator());
        dataGeneratorMap = new HashMap<>();
    }

    private DataLineGenerator buildDataLineGenerator(DataLineConfig dataLineConfig) {
        LineFunctionDoubleImpl functionDouble = new LineFunctionDoubleImpl(dataLineConfig);
        return new DataLineGeneratorDouble(dataConfig, dataLineConfig, functionDouble, indexToXDouble);
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
