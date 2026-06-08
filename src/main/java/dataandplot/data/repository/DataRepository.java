package dataandplot.data.repository;

import dataandplot.config.DataLineConfig;
import dataandplot.data.dataset.DataSet;
import dataandplot.data.range.DataBounds;

public interface DataRepository {
    void setDataSize(int dataSize);
    DataSet getDataSet(final DataLineConfig dataLineConfig);
    DataBounds getDataRange();
    void clear();
}
