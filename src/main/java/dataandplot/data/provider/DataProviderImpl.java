package dataandplot.data.provider;

import dataandplot.config.ConfigManager;
import dataandplot.config.DataConfig;
import dataandplot.config.DataLineConfig;
import dataandplot.data.generator.builder.DataLineGeneratorBuilder;
import dataandplot.data.generator.builder.DataLineGeneratorBuilderImpl;
import dataandplot.data.generator.dataline.DataLineGenerator;
import dataandplot.data.repository.DataRepository;
import dataandplot.data.dataset.DataSet;
import dataandplot.data.range.MinMaxDouble;
import dataandplot.data.range.RangeChangeListener;
import dataandplot.data.range.RangeType;

import java.util.ArrayList;
import java.util.List;


public class DataProviderImpl implements DataProvider {

    private final List<RangeChangeListener> listeners = new ArrayList<>();
    private final ConfigManager configManager;
    private final DataRepository dataRepository;
    private DataLineGeneratorBuilder dataLineGeneratorBuilder;

    public DataProviderImpl(final ConfigManager configManager, final DataRepository dataRepository) {
        this.configManager = configManager;
        this.dataRepository = dataRepository;
    }

    @Override
    public void buildData() {
        DataConfig dataConfig = configManager.getDataConfig();
        //IO.println("DataProviderImpl.buildData()   dataConfig=" + dataConfig);
        dataRepository.setDataSize(dataConfig.getSize());
        dataLineGeneratorBuilder = new DataLineGeneratorBuilderImpl(dataConfig);
        List<DataLineConfig> dataLineConfigs = dataConfig.getDataLineConfigs();
        //IO.println("DataProviderImpl.buildData()   dataLineConfigs=" + dataLineConfigs);
        dataLineConfigs.forEach(dlc -> {
            DataLineGenerator generator = dataLineGeneratorBuilder.getDataLineGenerator(dlc);
            DataSet dataSet = dataRepository.getDataSet(dlc);
            generator.populateDataSet(dataSet);
        });
        updateDataRangeChangedListeners();
    }

    private void updateDataRangeChangedListeners() {
        MinMaxDouble range = dataRepository.getDataRange();
        listeners.forEach(l -> l.onDataRangeChanged(RangeType.PHYSICAL_RANGE, range));
    }

    @Override
    public void addDataRangeChangeListener(RangeChangeListener listener) {
        listeners.add(listener);
    }



}
