package dataandplot;

import dataandplot.config.ConfigManager;
import dataandplot.config.ConfigManagerImpl;
import dataandplot.data.generator.config.*;
import dataandplot.data.provider.DataProvider;
import dataandplot.data.provider.DataProviderImpl;
import dataandplot.plot.UiContainer;

import javax.swing.*;
import java.awt.*;

// organisation of the data:
// 1. configuration
// 2. generation
// 3. graph configuration
// 4. plugin data to graph
public class PlotExample {

    private final ConfigManager configManager;
    private final DataProvider dataProvider;
    private final UiContainer ui;

    public PlotExample() {

        // 1. Data experiment configuration
        configManager = new ConfigManagerImpl();
        // 2. Build Data provider
        dataProvider = new DataProviderImpl(configManager);
        // 3. Build UI container
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

