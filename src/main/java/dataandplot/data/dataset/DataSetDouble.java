package dataandplot.data.dataset;

public interface DataSetDouble extends DataSet {
    void setData(double x, double y);
    double[] getDataAt(int index);
}
