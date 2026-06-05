package dataandplot.model.data;

import dataandplot.model.data.range.MinMaxDouble;
import dataandplot.model.data.range.UpdatableRangeDouble;

public class DataSetDoubleImpl implements DataSetDouble {

    private final double[][] data;
    private int dataIndex;
    private final UpdatableRangeDouble updatableRange;
    private MinMaxDouble minMaxDouble;

    public DataSetDoubleImpl(int size) {
        data = new double[size][2];
        updatableRange = new UpdatableRangeDouble();
        dataIndex = -1;
    }

    public void setData(double x, double y) {
        dataIndex++;
        if (dataIndex < data.length) {
            data[dataIndex][0] = x;
            data[dataIndex][1] = y;
            updatableRange.updateRange(x, y);
        } else {
            throw new RuntimeException("Should not be here.");
        }
    }

    @Override
    public void endOfData() {
        minMaxDouble = updatableRange.getMinMaxDouble();
    }

    @Override
    public MinMaxDouble getDataRange() {
        return minMaxDouble;
    }

    @Override
    public int getDataLength() {
        return data.length;
    }

    @Override
    public double[] getDataAt(int index) {
        if (dataIndex < data.length) {
            return data[index];
        } else {
            throw new RuntimeException("Should not be here.");
        }
    }
}
