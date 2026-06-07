package dataandplot.data.repository;

import dataandplot.config.DataLineConfig;
import dataandplot.model.data.DataSet;
import dataandplot.model.data.range.MinMaxDouble;

public interface DataRepository {
    void setDataSize(int dataSize);
    DataSet getDataSet(final DataLineConfig dataLineConfig);
    MinMaxDouble getDataRange();
}
