package dataandplot.data.provider;

import dataandplot.data.generator.config.GeneratorInfo;
import dataandplot.data.generator.step.FunctionStepDataGenerator;
import dataandplot.data.holder.DataHolderFloat;
import dataandplot.data.holder.FloatDataPoint;
import dataandplot.plot.converter.PixelConverter;
import dataandplot.plot.converter.PixelConverterImpl;
import dataandplot.util.DataRangeFloat;

import java.awt.geom.GeneralPath;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;

public class DataProviderImpl implements DataProvider {
    private final GeneratorInfo generatorInfo;
    private final DataHolderFloat dataHolder;
    private final FunctionStepDataGenerator stepDataGenerator;
    private final PixelConverter dataToPixelConverter;
    private Path2D.Float convertedPath;
    private boolean updatePath = true;

    public DataProviderImpl(final GeneratorInfo generatorInfo,
                            final FunctionStepDataGenerator stepDataGenerator,
                            final DataHolderFloat dataHolder) {
        this.generatorInfo = generatorInfo;
        this.stepDataGenerator = stepDataGenerator;
        this.dataHolder = dataHolder;
        generateData();
        DataRangeFloat range = dataHolder.getDataRange();
        dataToPixelConverter = new PixelConverterImpl(range);
    }

    public void setPlotBounds(Rectangle2D plotBounds) {
        dataToPixelConverter.setPlotBounds(plotBounds);
        updatePath = true;
    }

    public PixelConverter getDataToPixelConverter() {
        return dataToPixelConverter;
    }

    @Override
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

    private void generateData() {
        int numSamples = generatorInfo.size();
        for (int i = 0; i < numSamples; i++) {
            double value = stepDataGenerator.generate(i * generatorInfo.stepMultiplicator());
            dataHolder.setData(i, (float) value);
        }
        dataHolder.closeDataPath();
    }

}
