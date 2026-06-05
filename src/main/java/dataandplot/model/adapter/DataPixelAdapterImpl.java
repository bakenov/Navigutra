package dataandplot.model.adapter;

import dataandplot.config.ConfigManager;
import dataandplot.config.DataConfig;
import dataandplot.data.generator.step.StepDataGenerator;
import dataandplot.data.holder.DataHolderFloat;
import dataandplot.data.holder.FloatDataPoint;
import dataandplot.plot.converter.PixelConverter;
import dataandplot.plot.converter.PixelConverterImpl;

import java.awt.geom.GeneralPath;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;

public class DataPixelAdapterImpl implements DataPixelAdapter {
    private final ConfigManager configManager;

    private final PixelConverter dataToPixelConverter;
    private String dataConfigFile;

    private DataConfig generatorInfo;
    private DataHolderFloat dataHolder;
    private StepDataGenerator stepDataGenerator;

    private Path2D.Float convertedPath;
    private boolean updatePath = true;


    public DataPixelAdapterImpl(final ConfigManager configManager) {
        this.configManager = configManager;
        dataToPixelConverter = new PixelConverterImpl(configManager.getUIConfig().plotInsets());
//        DataRangeFloat range = dataHolder.getDataRange();
//        dataToPixelConverter = new PixelConverterImpl(range);
    }

    public void setPlotBounds(Rectangle2D plotBounds) {
        dataToPixelConverter.setPlotBounds(plotBounds);
        updatePath = true;
    }

    public PixelConverter getDataToPixelConverter() {
        return dataToPixelConverter;
    }

    public Path2D.Float getDataPathInPixels() {
        if (!updatePath)
            return convertedPath;

        int size = dataHolder.getDataLength();
        convertedPath = new Path2D.Float(GeneralPath.WIND_NON_ZERO, size);
        movePath(dataHolder.getFloatDataPoint(0));
        if (size > 0) {
            for (int i = 1; i < size; i++) {
                addToPath(dataHolder.getFloatDataPoint(i));
            }
        }
        return convertedPath;
    }

    private void addToPath(FloatDataPoint point) {
        int pixelX = dataToPixelConverter.physicalToPixelX(point.x());
        int pixelY = dataToPixelConverter.physicalToPixelY(point.y());
//        IO.println("DataProviderImpl.addToPath()     point:" + point + " --> (" +
//                pixelX + ":" + pixelY + ") --> " + dataToPixelConverter.pixelToPhysical(pixelX, pixelY));
        convertedPath.lineTo(pixelX, pixelY);
    }

    private void movePath(FloatDataPoint point) {
        int pixelX = dataToPixelConverter.physicalToPixelX(point.x());
        int pixelY = dataToPixelConverter.physicalToPixelY(point.y());
//        IO.println("DataProviderImpl.movePath()     point:" + point + " --> (" +
//                pixelX + ":" + pixelY + ") --> " + dataToPixelConverter.pixelToPhysical(pixelX, pixelY));
        convertedPath.moveTo(pixelX, pixelY);
    }

}
