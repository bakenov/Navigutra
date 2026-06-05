package dataandplot.data.generator.builder;

import dataandplot.config.DataConfig;
import dataandplot.config.DataLineConfig;
import dataandplot.data.generator.dataline.DataLineGenerator;
import dataandplot.data.generator.dataline.DataLineGeneratorDouble;
import dataandplot.data.generator.function.LineFunctionDoubleImpl;
import dataandplot.model.data.index.IndexToXDouble;
import dataandplot.model.data.index.IndexToXDoubleImpl;

public class DataLineGeneratorBuilderImpl implements DataLineGeneratorBuilder {

    private final DataConfig dataConfig;
    private final IndexToXDouble indexToXDouble;

    public DataLineGeneratorBuilderImpl(DataConfig dataConfig) {
        this.dataConfig = dataConfig;
        indexToXDouble = new IndexToXDoubleImpl(dataConfig.getStepMultiplicator());
    }

    @Override
    public DataLineGenerator buildDataLineGenerator(DataLineConfig dataLineConfig) {
        switch (dataLineConfig.dataType()) {
            case DOUBLE:
                LineFunctionDoubleImpl functionDouble = new LineFunctionDoubleImpl(dataLineConfig);
                return new DataLineGeneratorDouble(dataLineConfig.name(), dataConfig.getSize(), functionDouble, indexToXDouble);
            case FLOAT: {
            }
        }
        return null;
    }


}
