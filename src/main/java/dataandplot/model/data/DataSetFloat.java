package dataandplot.model.data;

import dataandplot.model.data.range.MinMaxFloat;

public interface DataSetFloat extends DataSet {
    void setData(float x, float y);
    float[] getDataAt(int index);
}
