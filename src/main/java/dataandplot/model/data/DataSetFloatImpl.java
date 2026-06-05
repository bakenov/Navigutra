package dataandplot.model.data;

import dataandplot.model.data.range.MinMaxFloat;
import dataandplot.model.data.range.UpdatableRangeDouble;
import dataandplot.model.data.range.UpdatableRangeFloat;

public class DataSetFloatImpl implements DataSetFloat {

    private final float[][] data;
    private int dataIndex;
    private final UpdatableRangeFloat updatableRange;
    private MinMaxFloat minMax;

    public DataSetFloatImpl(int size) {
        data = new float[size][2];
        updatableRange = new UpdatableRangeFloat();
        dataIndex = -1;
    }

    @Override
    public void setData(float x, float y) {
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
    public MinMaxFloat getDataRange() {
        return minMax;
    }

    @Override
    public float[] getDataAt(int index) {
        if (dataIndex < data.length) {
            return data[index];
        } else {
            throw new RuntimeException("Should not be here.");
        }
    }

    @Override
    public void endOfData() {
        minMax = updatableRange.getMinMaxFloat();
    }

    @Override
    public int getDataLength() {
        return data.length;
    }
}
