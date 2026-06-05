package dataandplot;

import dataandplot.config.ConfigManager;
import dataandplot.config.ConfigManagerImpl;
import dataandplot.data.provider.DataProvider;
import dataandplot.data.provider.DataProviderImpl;
import dataandplot.model.adapter.DataPixelAdapter;
import dataandplot.model.adapter.DataPixelAdapterImpl;
import dataandplot.plot.UiContainer;

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
        // 2. Build data to pixel adapter
        DataPixelAdapter adapter = new DataPixelAdapterImpl(configManager);
        // 3. Build Data provider
        DataProvider dataProvider = new DataProviderImpl(configManager, adapter);
        // 4. Build UI container
        ui = new UiContainer(configManager, dataProvider);
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

