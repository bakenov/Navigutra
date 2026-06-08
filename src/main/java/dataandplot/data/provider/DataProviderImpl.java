package dataandplot.data.provider;

import dataandplot.config.ConfigManager;
import dataandplot.config.DataConfig;
import dataandplot.config.DataLineConfig;
import dataandplot.data.generator.dataline.builder.DataLineGeneratorBuilder;
import dataandplot.data.generator.dataline.builder.DataLineGeneratorBuilderImpl;
import dataandplot.data.generator.dataline.DataLineGenerator;
import dataandplot.data.repository.DataRepository;
import dataandplot.data.dataset.DataSet;
import dataandplot.data.range.DataBounds;
import dataandplot.data.range.RangeChangeListener;
import dataandplot.data.range.RangeType;

import java.util.ArrayList;
import java.util.List;


public class DataProviderImpl implements DataProvider {

    private final List<RangeChangeListener> listeners = new ArrayList<>();
    private final ConfigManager configManager;
    private final DataRepository dataRepository;
    private final DataLineGeneratorBuilder dataLineGeneratorBuilder;

    public DataProviderImpl(final ConfigManager configManager, final DataRepository dataRepository) {
        this.configManager = configManager;
        this.dataRepository = dataRepository;
        dataLineGeneratorBuilder = new DataLineGeneratorBuilderImpl();
    }

    @Override
    public void buildData() {
        // if there is previous data clear all caches
        dataRepository.clear();
        DataConfig dataConfig = configManager.getDataConfig();
        dataRepository.setDataSize(dataConfig.getSize());
        dataLineGeneratorBuilder.setDataConfig(dataConfig);
        List<DataLineConfig> dataLineConfigs = dataConfig.getDataLineConfigs();
        dataLineConfigs.forEach(dlc -> {
            DataLineGenerator generator = dataLineGeneratorBuilder.getDataLineGenerator(dlc);
            DataSet dataSet = dataRepository.getDataSet(dlc);
            generator.populateDataSet(dataSet);
        });
        updateDataRangeChangedListeners();
    }

    private void updateDataRangeChangedListeners() {
        DataBounds range = dataRepository.getDataRange();
        listeners.forEach(l -> l.onDataRangeChanged(RangeType.PHYSICAL_RANGE, range));
    }

    @Override
    public void addDataRangeChangeListener(RangeChangeListener listener) {
        listeners.add(listener);
    }



}
