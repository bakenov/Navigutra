package dataandplot.data.provider;

import dataandplot.config.ConfigManager;
import dataandplot.config.DataConfig;
import dataandplot.config.DataLineConfig;
import dataandplot.data.generator.builder.DataLineGeneratorBuilder;
import dataandplot.data.generator.builder.DataLineGeneratorBuilderImpl;
import dataandplot.data.generator.dataline.DataLineGenerator;
import dataandplot.data.repository.DataRepository;
import dataandplot.model.data.DataSet;

import java.util.List;


public class DataProviderImpl implements DataProvider {

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
        dataRepository.setDataSize(dataConfig.getSize());
        dataLineGeneratorBuilder = new DataLineGeneratorBuilderImpl(dataConfig);
        List<DataLineConfig> dataLineConfigs = dataConfig.getDataLineConfigs();
        IO.println("DataProviderImpl.buildData()   dataConfig=" + dataConfig);
        IO.println("DataProviderImpl.buildData()   dataLineConfigs=" + dataLineConfigs);
        dataLineConfigs.forEach(dlc -> {
            DataLineGenerator generator = dataLineGeneratorBuilder.getDataLineGenerator(dlc);
            DataSet dataSet = dataRepository.getDataSet(dlc);
            generator.populateDataSet(dataSet);
        });
    }

}
