package dataandplot.data.repository;

import dataandplot.config.DataLineConfig;
import dataandplot.data.dataset.DataSet;
import dataandplot.data.dataset.DataSetImpl;
import dataandplot.data.range.DataBounds;
import dataandplot.data.range.UpdatableDataBounds;

import java.util.HashMap;
import java.util.Map;

public class DataRepositoryImpl implements DataRepository {

    private final Map<String, DataSet> dataSetMap;
    private final UpdatableDataBounds updatableRange;
    private int dataSize;

    public DataRepositoryImpl() {
        this.dataSetMap = new HashMap<>();
        this.updatableRange = new UpdatableDataBounds();
    }

    public void clear() {
        dataSetMap.clear();
        updatableRange.reset();
        dataSize = 0;
    }

    // Data config updated
    @Override
    public void setDataSize(int dataSize) {
        this.dataSize = dataSize;
        dataSetMap.clear();
    }

    @Override
    public DataSet getDataSet(final DataLineConfig dataLineConfig) {
        String name = dataLineConfig.name();
        if (!dataSetMap.containsKey(name)) {
            dataSetMap.put(name, buildDataSet(dataLineConfig) );
        }
        return dataSetMap.get(name);
    }

    @Override
    public DataBounds getDataRange() {
        updatableRange.reset();
        dataSetMap.values().forEach(dataSet -> {
            DataBounds range = dataSet.getDataRange();
            updatableRange.updateRange(range);
        });
        return updatableRange.getDataBounds();
    }

    private DataSet buildDataSet(DataLineConfig dataLineConfig) {
        return new DataSetImpl(dataSize);
    }
}
