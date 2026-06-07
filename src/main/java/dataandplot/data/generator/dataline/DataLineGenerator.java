package dataandplot.data.generator.dataline;

import dataandplot.data.dataset.DataSet;

public interface DataLineGenerator {
    String dataLineName();
    void populateDataSet(DataSet dataSet);
}
