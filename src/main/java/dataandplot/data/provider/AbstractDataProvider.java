package dataandplot.data.provider;

import dataandplot.config.ConfigManager;
import dataandplot.config.DataConfig;
import dataandplot.data.generator.step.StepDataGenerator;
import dataandplot.data.holder.DataHolderFloat;
import dataandplot.data.holder.FloatDataPoint;
import dataandplot.data.provider.builder.DataProviderBuilder;
import dataandplot.data.provider.builder.DataProviderBuilderImpl;
import dataandplot.plot.converter.PixelConverter;
import dataandplot.plot.converter.PixelConverterImpl;

import java.awt.geom.GeneralPath;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.util.Properties;

public class AbstractDataProvider implements DataProvider {

    private final ConfigManager configManager;
    private final PixelConverter dataToPixelConverter;
    private String dataConfigFile;

    private DataConfig generatorInfo;
    private DataHolderFloat dataHolder;
    private StepDataGenerator stepDataGenerator;

    private Path2D.Float convertedPath;
    private boolean updatePath = true;

    public AbstractDataProvider(final ConfigManager configManager) {
        this.configManager = configManager;
        dataToPixelConverter = new PixelConverterImpl(configManager.getUIConfig().plotInsets());
    }




//    public DataProviderImpl(final GeneratorInfo generatorInfo,
//                            final FunctionStepDataGenerator stepDataGenerator,
//                            final DataHolderFloat dataHolder) {
//        this.generatorInfo = generatorInfo;
//        this.stepDataGenerator = stepDataGenerator;
//        this.dataHolder = dataHolder;
//        generateData();
//        DataRangeFloat range = dataHolder.getDataRange();
//        dataToPixelConverter = new PixelConverterImpl(range);
//    }

    @Override
    public void buildData() {

    }

    private void generateData() {
        int numSamples = generatorInfo.getSize();
        for (int i = 0; i < numSamples; i++) {
//            double value = stepDataGenerator.generate(i * generatorInfo.getStepMultiplicator());
//            dataHolder.setData(i, (float) value);
        }
        dataHolder.closeDataPath();
    }


    public void buildData(final Properties dataConfig) {
        if(dataConfig == null)
            return;
        DataProviderBuilder dataBuilder = new DataProviderBuilderImpl();
//        GeneratorInfo dataInfo = new GeneratorInfo(config);
//        IO.println("PlotExample()   dataInfo:" + dataInfo);
//        dataBuilder.build(dataInfo, config);
        //DataProvider dataProvider = dataBuilder.build(dataInfo, config);
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


}
