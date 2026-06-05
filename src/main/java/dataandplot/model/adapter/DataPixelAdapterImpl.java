package dataandplot.model.adapter;

import dataandplot.config.ConfigManager;
import dataandplot.config.DataConfig;
import dataandplot.config.DataLineConfig;
import dataandplot.data.generator.dataline.DataLineGenerator;
import dataandplot.data.repository.DataRepository;
import dataandplot.model.data.DataSet;
import dataandplot.model.data.range.MinMaxDouble;
import dataandplot.model.data.range.UpdatableRangeDouble;
import dataandplot.plot.Insets;
import dataandplot.plot.converter.PixelConverter;
import dataandplot.plot.converter.PixelConverterImpl;


import java.awt.geom.GeneralPath;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataPixelAdapterImpl implements DataPixelAdapter {

    private final ConfigManager configManager;
    private final DataRepository dataRepository;
    private final UpdatableRangeDouble updatableRange;
    private final Insets plotInsets;
    private final PixelConverter dataToPixelConverter;
    private final Map<String, Path2D.Float> convertedPathMap;

    private MinMaxDouble rangeForAllDatasets;

    private Path2D.Float convertedPath;
    private boolean updatePath = true;


    public DataPixelAdapterImpl(final ConfigManager configManager, final DataRepository dataRepository) {
        this.configManager = configManager;
        this.dataRepository = dataRepository;
        this.plotInsets = configManager.getUIConfig().plotInsets();
        this.dataToPixelConverter = new PixelConverterImpl(configManager.getUIConfig().plotInsets());
        this.updatableRange = new UpdatableRangeDouble();
        this.convertedPathMap = new HashMap<>();
    }

    public void setPlotBounds(Rectangle2D plotBounds) {
        dataToPixelConverter.setPlotBounds(plotBounds);
        updatePath = true;
    }

    @Override
    public void allDataGenerated() {
        updatableRange.reset();
        DataConfig dataConfig = configManager.getDataConfig();
        List<DataLineConfig> dataLineConfigs = dataConfig.getDataLineConfigs();
        dataLineConfigs.forEach(dlc -> {
            DataSet dataSet = dataRepository.getDataSet(dlc);
            MinMaxDouble range = dataSet.getDataRange();
            updatableRange.updateRange(range);
        });
        rangeForAllDatasets = updatableRange.getMinMaxDouble();
    }

    public void buildAllPaths() {
        DataConfig dataConfig = configManager.getDataConfig();
        List<DataLineConfig> dataLineConfigs = dataConfig.getDataLineConfigs();
        dataLineConfigs.forEach(dlc -> {
            DataSet dataSet = dataRepository.getDataSet(dlc);
            Path2D.Float path = convertedPath = new Path2D.Float(GeneralPath.WIND_NON_ZERO, dataSet.getDataLength());
            dataSet.populatePath(dataToPixelConverter, convertedPath);
            convertedPathMap.put(dlc.name(), path);
        });
    }

    public PixelConverter getDataToPixelConverter() {
        return dataToPixelConverter;
    }

    public Path2D.Float getDataPathInPixels() {
        if (!updatePath)
            return convertedPath;

//        int size = dataHolder.getDataLength();
//        convertedPath = new Path2D.Float(GeneralPath.WIND_NON_ZERO, size);
//        movePath(dataHolder.getFloatDataPoint(0));
//        if (size > 0) {
//            for (int i = 1; i < size; i++) {
//                addToPath(dataHolder.getFloatDataPoint(i));
//            }
//        }
        return convertedPath;
    }

//    private void addToPath(FloatDataPoint point) {
//        int pixelX = dataToPixelConverter.physicalToPixelX(point.x());
//        int pixelY = dataToPixelConverter.physicalToPixelY(point.y());
////        IO.println("DataProviderImpl.addToPath()     point:" + point + " --> (" +
////                pixelX + ":" + pixelY + ") --> " + dataToPixelConverter.pixelToPhysical(pixelX, pixelY));
//        convertedPath.lineTo(pixelX, pixelY);
//    }
//
//    private void movePath(FloatDataPoint point) {
//        int pixelX = dataToPixelConverter.physicalToPixelX(point.x());
//        int pixelY = dataToPixelConverter.physicalToPixelY(point.y());
////        IO.println("DataProviderImpl.movePath()     point:" + point + " --> (" +
////                pixelX + ":" + pixelY + ") --> " + dataToPixelConverter.pixelToPhysical(pixelX, pixelY));
//        convertedPath.moveTo(pixelX, pixelY);
//    }

}
