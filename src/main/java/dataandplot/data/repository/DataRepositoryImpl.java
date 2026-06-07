package dataandplot.data.repository;

import dataandplot.config.DataLineConfig;
import dataandplot.data.dataset.DataSet;
import dataandplot.data.dataset.DataSetDoubleImpl;
import dataandplot.data.dataset.DataSetFloatImpl;
import dataandplot.data.range.MinMaxDouble;
import dataandplot.data.range.UpdatableRangeDouble;

import java.util.HashMap;
import java.util.Map;

public class DataRepositoryImpl implements DataRepository {

    private final Map<String, DataSet> dataSetMap;
    private final UpdatableRangeDouble updatableRange;
    private int dataSize;

    public DataRepositoryImpl() {
        this.dataSetMap = new HashMap<>();
        this.updatableRange = new UpdatableRangeDouble();
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
    public MinMaxDouble getDataRange() {
        updatableRange.reset();
        dataSetMap.values().forEach(dataSet -> {
            MinMaxDouble range = dataSet.getDataRange();
            updatableRange.updateRange(range);
        });
        return updatableRange.getMinMaxDouble();
    }

    private DataSet buildDataSet(DataLineConfig dataLineConfig) {
        return switch (dataLineConfig.dataType()) {
            case DOUBLE -> new DataSetDoubleImpl(dataSize);
            case FLOAT -> new DataSetFloatImpl(dataSize);
            default -> null;
        };
    }
}
