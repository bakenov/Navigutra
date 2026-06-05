package dataandplot.model.data;

import dataandplot.model.data.range.MinMaxDouble;

public interface DataSetDouble extends DataSet {
    void setData(double x, double y);
    void endOfData();
    MinMaxDouble getDataRange();
    int getDataLength();
    double[] getDataAt(int index);
}
