package dataandplot.model.data;

import dataandplot.model.data.range.MinMaxDouble;

public interface DataSetDouble extends DataSet {
    void setData(double x, double y);
    MinMaxDouble getDataRange();
    double[] getDataAt(int index);
}
