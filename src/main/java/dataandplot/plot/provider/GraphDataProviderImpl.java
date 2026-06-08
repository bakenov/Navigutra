package dataandplot.plot.provider;

import dataandplot.config.ConfigManager;
import dataandplot.config.DataConfig;
import dataandplot.config.DataLineConfig;
import dataandplot.data.repository.DataRepository;
import dataandplot.data.dataset.DataSet;
import dataandplot.data.range.DataBounds;
import dataandplot.data.range.RangeType;
import dataandplot.plot.converter.AreaToDataConverter;

import java.awt.geom.GeneralPath;
import java.awt.geom.Path2D;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphDataProviderImpl implements GraphDataProvider {

    private final ConfigManager configManager;
    private final AreaToDataConverter areaToDataConverter;
    private final DataRepository dataRepository;
    private final Map<String, Path2D.Float> convertedPathMap;

    public GraphDataProviderImpl(final ConfigManager configManager, final AreaToDataConverter areaToDataConverter, final DataRepository dataRepository) {
        this.configManager = configManager;
        this.areaToDataConverter = areaToDataConverter;
        this.dataRepository = dataRepository;
        this.convertedPathMap = new HashMap<>();
    }

    @Override
    public void onDataRangeChanged(RangeType rangeType, DataBounds range) {
        areaToDataConverter.onDataRangeChanged(rangeType, range);
        if (areaToDataConverter.isReady()) {
            updateDataToPixelPaths();
        }
    }

    private void updateDataToPixelPaths() {
        DataConfig dataConfig = configManager.getDataConfig();
        List<DataLineConfig> dataLineConfigs = dataConfig.getDataLineConfigs();
        dataLineConfigs.forEach(dlc -> {
            Path2D.Float path = buildAndPopulate(dlc);
            convertedPathMap.put(dlc.name(), path);
        });
    }

    private Path2D.Float buildAndPopulate(DataLineConfig dataLineConfig) {
        DataSet dataSet = dataRepository.getDataSet(dataLineConfig);
        Path2D.Float path = new Path2D.Float(GeneralPath.WIND_NON_ZERO, dataSet.getDataLength());
        dataSet.populatePath(areaToDataConverter, path);
        return path;
    }

    @Override
    public Path2D.Float getDataPathInPixels(String pathName) {
        if (convertedPathMap.containsKey(pathName)) {
            return convertedPathMap.get(pathName);
        }
        throw new RuntimeException("Data Path not found for name:" + pathName);
    }

    @Override
    public void clear() {
        convertedPathMap.clear();
    }

    @Override
    public boolean isReady() {
        return areaToDataConverter.isReady();
    }

    @Override
    public AreaToDataConverter getAreaToDataConverter() {
        return areaToDataConverter;
    }

}
