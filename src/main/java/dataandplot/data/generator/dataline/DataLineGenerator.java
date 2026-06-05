package dataandplot.data.generator.dataline;

import dataandplot.model.data.DataSet;

public interface DataLineGenerator {
    String getDataLineName();
    DataSet getDataSet();
    void generateAt(int index);
}
