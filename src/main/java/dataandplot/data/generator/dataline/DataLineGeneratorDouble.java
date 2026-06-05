package dataandplot.data.generator.dataline;

import dataandplot.model.data.DataSet;
import dataandplot.model.data.DataSetDouble;
import dataandplot.model.data.DataSetDoubleImpl;
import dataandplot.model.data.index.IndexToXDouble;

import java.util.function.DoubleUnaryOperator;

public class DataLineGeneratorDouble implements DataLineGenerator {

    private final String dataLineName;
    private final DoubleUnaryOperator function;
    private final DataSetDouble dataSet;
    private final IndexToXDouble indexToXDouble;

    public DataLineGeneratorDouble(String dataLineName, int size, final DoubleUnaryOperator function, final IndexToXDouble indexToXDouble) {
        this.dataLineName = dataLineName;
        this.function = function;
        this.indexToXDouble = indexToXDouble;
        dataSet = new DataSetDoubleImpl(size);
    }

    @Override
    public DataSet getDataSet() {
        return dataSet;
    }

    @Override
    public void generateAt(int index) {
        double x = indexToXDouble.toXbyIndex(index);
        dataSet.setData(x, function.applyAsDouble(index));
    }

    public DoubleUnaryOperator getFunction() {
        return function;
    }

    public IndexToXDouble getIndexToXDouble() {
        return indexToXDouble;
    }

    public String getDataLineName() {
        return dataLineName;
    }

}
