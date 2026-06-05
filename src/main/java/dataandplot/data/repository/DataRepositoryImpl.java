package dataandplot.data.repository;

import dataandplot.config.DataLineConfig;
import dataandplot.model.data.DataSet;
import dataandplot.model.data.DataSetDoubleImpl;
import dataandplot.model.data.DataSetFloatImpl;

import java.util.HashMap;
import java.util.Map;

public class DataRepositoryImpl implements DataRepository {

    private final Map<String, DataSet> dataSetMap;
    private int dataSize;

    public DataRepositoryImpl() {
        this.dataSetMap = new HashMap<>();
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

    private DataSet buildDataSet(DataLineConfig dataLineConfig) {
        return switch (dataLineConfig.dataType()) {
            case DOUBLE -> new DataSetDoubleImpl(dataSize);
            case FLOAT -> new DataSetFloatImpl(dataSize);
            default -> null;
        };
    }
}
