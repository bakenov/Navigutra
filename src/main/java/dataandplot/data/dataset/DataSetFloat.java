package dataandplot.data.dataset;

public interface DataSetFloat extends DataSet {
    void setData(float x, float y);
    float[] getDataAt(int index);
}
