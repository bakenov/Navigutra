package dataandplot.data.holder;

import dataandplot.util.DataRangeFloat;

public interface DataHolderFloat {

    void setData(float x, float y);
    void closeDataPath();
    DataRangeFloat getDataRange();
    int getDataLength();
    FloatDataPoint getFloatDataPoint(int index);
}
