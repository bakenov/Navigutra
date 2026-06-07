package dataandplot;

import dataandplot.config.ConfigManager;
import dataandplot.config.ConfigManagerImpl;
import dataandplot.data.provider.DataProvider;
import dataandplot.data.provider.DataProviderImpl;
import dataandplot.data.repository.DataRepository;
import dataandplot.data.repository.DataRepositoryImpl;
import dataandplot.plot.area.manager.PlotAreaManager;
import dataandplot.plot.area.manager.PlotAreaManagerImpl;
import dataandplot.plot.UiContainer;
import dataandplot.plot.converter.AreaToDataConverter;
import dataandplot.plot.converter.AreaToDataConverterImpl;
import dataandplot.plot.provider.GraphDataProvider;
import dataandplot.plot.provider.GraphDataProviderImpl;

import javax.swing.*;

// organisation of the data:
// 1. configuration
// 2. generation
// 3. graph configuration
// 4. plugin data to graph
public class PlotExample {

    private final UiContainer ui;

    public PlotExample() {

        // 1. Data experiment configuration
        ConfigManager configManager = new ConfigManagerImpl();
        // 2. Build Data repository
        DataRepository dataRepository = new DataRepositoryImpl();
        // 3. Build Data provider
        DataProvider dataProvider = new DataProviderImpl(configManager, dataRepository);
        // 4. DataConverter
        AreaToDataConverter areaToDataConverter = new AreaToDataConverterImpl();
        // 5. Build graph data provider
        GraphDataProvider graphDataProvider = new GraphDataProviderImpl(configManager, areaToDataConverter, dataRepository);
        dataProvider.addDataRangeChangeListener(graphDataProvider);
        // 6. Build PlotAreaManager
        PlotAreaManager plotAreaManager = new PlotAreaManagerImpl(configManager, graphDataProvider);
        dataProvider.addDataRangeChangeListener(plotAreaManager);
        // 7. Build UI container
        ui = new UiContainer(configManager, dataProvider, plotAreaManager, areaToDataConverter);
        ui.getPlotPanel().addDataRangeChangeListener(plotAreaManager);
    }

    private void showFrame() {
        ui.showFrame();
    }

    void main() {
        SwingUtilities.invokeLater(() -> {
            PlotExample app = new PlotExample();
            app.showFrame();
        });
    }
}

