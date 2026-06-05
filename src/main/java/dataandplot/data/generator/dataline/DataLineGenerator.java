package dataandplot.data.generator.dataline;

import dataandplot.model.data.DataSet;

public interface DataLineGenerator {
    String dataLineName();
    void populateDataSet(DataSet dataSet);
}
