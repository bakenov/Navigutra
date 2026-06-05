package dataandplot.data.provider;

import dataandplot.config.ConfigManager;
import dataandplot.config.DataConfig;
import dataandplot.config.DataLineConfig;
import dataandplot.data.generator.builder.DataLineGeneratorBuilder;
import dataandplot.data.generator.builder.DataLineGeneratorBuilderImpl;
import dataandplot.data.generator.dataline.DataLineGenerator;
import dataandplot.model.adapter.DataPixelAdapter;
import dataandplot.model.data.DataSet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DataProviderImpl implements DataProvider {

    private final ConfigManager configManager;
    private DataLineGeneratorBuilder dataLineGeneratorBuilder;
    private final Map<String, DataLineGenerator> dataGeneratorMap;
    private final Map<String, DataSet> dataSetMap;


    private final DataPixelAdapter adapter;


    public DataProviderImpl(final ConfigManager configManager, final DataPixelAdapter adapter) {
        this.configManager = configManager;
        this.adapter = adapter;
        dataGeneratorMap = new HashMap<>();
        dataSetMap = new HashMap<>();
    }

    @Override
    public void buildData() {
        DataConfig dataConfig = configManager.getDataConfig();
        dataLineGeneratorBuilder = new DataLineGeneratorBuilderImpl(dataConfig);

        List<DataLineConfig> dataLineConfigs = dataConfig.getDataLineConfigs();
        dataLineConfigs.forEach(dlc -> {
            DataLineGenerator generator = dataLineGeneratorBuilder.buildDataLineGenerator(dlc);
            dataGeneratorMap.put(dlc.name(), generator);
        });

        int numSamples = dataConfig.getSize();
        for (int i = 0; i < numSamples; i++) {
            generateValueAt(i);
        }
        // all values generated
        dataGeneratorMap.values().forEach(g -> {
            dataSetMap.put(g.getDataLineName(), g.getDataSet());
//            adapter.
        });
        IO.println("DataProviderImpl.buildData()   dataConfig=" + dataConfig);
        IO.println("DataProviderImpl.buildData()   dataLineConfigs=" + dataLineConfigs);
        IO.println("DataProviderImpl.buildData()   dataGeneratorMap=" + dataGeneratorMap);
        IO.println("DataProviderImpl.buildData()   dataSetMap=" + dataSetMap);

    }

    private void generateValueAt(int index) {
        dataGeneratorMap.values().forEach(g ->g.generateAt(index));
    }

}
