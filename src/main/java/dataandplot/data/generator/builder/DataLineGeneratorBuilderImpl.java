package dataandplot.data.generator.builder;

import dataandplot.config.DataConfig;
import dataandplot.config.DataLineConfig;
import dataandplot.data.generator.dataline.DataLineGenerator;
import dataandplot.data.generator.dataline.DataLineGeneratorDouble;
import dataandplot.data.generator.function.LineFunctionDoubleImpl;
import dataandplot.model.data.index.IndexToXDouble;
import dataandplot.model.data.index.IndexToXDoubleImpl;

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
        switch (dataLineConfig.dataType()) {
            case DOUBLE:
                LineFunctionDoubleImpl functionDouble = new LineFunctionDoubleImpl(dataLineConfig);
                return new DataLineGeneratorDouble(dataConfig, dataLineConfig, functionDouble, indexToXDouble);
            case FLOAT: {
            }
        }
        return null;
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
