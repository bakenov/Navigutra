package dataandplot.data.generator.dataline;

import dataandplot.config.DataConfig;
import dataandplot.config.DataLineConfig;
import dataandplot.model.data.DataSet;
import dataandplot.model.data.DataSetDouble;
import dataandplot.model.data.DataSetDoubleImpl;
import dataandplot.model.data.index.IndexToXDouble;

import java.util.function.DoubleUnaryOperator;

public record DataLineGeneratorDouble(String dataLineName, int size, DoubleUnaryOperator function,
                                      IndexToXDouble indexToXDouble) implements DataLineGenerator {

    public DataLineGeneratorDouble(DataConfig dataConfig, DataLineConfig dataLineConfig, final DoubleUnaryOperator function, final IndexToXDouble indexToXDouble) {
        this(dataLineConfig.name(), dataConfig.getSize(), function, indexToXDouble);
    }

    @Override
    public void populateDataSet(DataSet dataSet) {
        if (dataSet instanceof DataSetDouble dataSetDouble) {
            for (int i = 0; i < size; i++) {
                double x = indexToXDouble.toXbyIndex(i);
                dataSetDouble.setData(x, function.applyAsDouble(x));
            }
        }
    }

}
