package dataandplot.data.generator.dataline.builder;

import dataandplot.config.DataConfig;
import dataandplot.config.DataLineConfig;
import dataandplot.data.generator.dataline.DataLineGenerator;
import dataandplot.data.generator.dataline.DataLineGeneratorImpl;
import dataandplot.data.generator.function.DataFunction;
import dataandplot.data.generator.function.LineFunctionImpl;
import dataandplot.data.generator.function.SineFunctionImpl;
import dataandplot.data.generator.index.IndexToXDouble;
import dataandplot.data.generator.index.IndexToXDoubleImpl;

import java.util.HashMap;
import java.util.Map;

public class DataLineGeneratorBuilderImpl implements DataLineGeneratorBuilder {

    private final Map<String, DataLineGenerator> dataGeneratorMap;
    private DataConfig dataConfig;

    public DataLineGeneratorBuilderImpl() {
        dataGeneratorMap = new HashMap<>();
    }

    public void setDataConfig(final DataConfig dataConfig) {
        this.dataConfig = dataConfig;
    }

    private DataLineGenerator buildDataLineGenerator(DataLineConfig dataLineConfig) {
        DataFunction dataFunction = null;
        switch (dataLineConfig.funType()) {
            case LINE:
                dataFunction = new LineFunctionImpl(dataLineConfig);
                break;
            case SINE:
                dataFunction = new SineFunctionImpl(dataLineConfig);
                break;
        }
        return new DataLineGeneratorImpl(dataConfig, dataLineConfig, dataFunction, dataFunction.indexToXDouble());
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
