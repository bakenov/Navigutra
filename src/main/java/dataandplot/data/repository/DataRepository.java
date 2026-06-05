package dataandplot.data.repository;

import dataandplot.config.DataLineConfig;
import dataandplot.model.data.DataSet;

public interface DataRepository {
    void setDataSize(int dataSize);
    DataSet getDataSet(final DataLineConfig dataLineConfig);
}
