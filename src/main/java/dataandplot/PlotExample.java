package dataandplot;

import dataandplot.data.generator.config.*;
import dataandplot.data.provider.DataProvider;
import dataandplot.data.provider.builder.DataProviderBuilder;
import dataandplot.data.provider.builder.DataProviderBuilderImpl;
import dataandplot.plot.PlotPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Properties;

import static dataandplot.util.Utils.loadProperties;

public class PlotExample extends JFrame {

    public PlotExample() {

        // organisation of the data:
        // 1. configuration
        // 2. generation
        // 3. graph configuration
        // 4. plugin data to graph

        Properties config = loadProperties("configSineLineNoise.properties");
        if(config == null)
            return;
        DataProviderBuilder dataBuilder = new DataProviderBuilderImpl();
        GeneratorInfo dataInfo = new GeneratorInfo(config);
        IO.println("PlotExample()   dataInfo:" + dataInfo);
        DataProvider dataProvider = dataBuilder.build(dataInfo, config);
        // 1. Create the content panel
        JPanel contentPanel = createParentPanel();
        // 2. Create the plot panel
        PlotPanel plotPanel = new PlotPanel(dataProvider);
        contentPanel.add(plotPanel, BorderLayout.CENTER);
        add(contentPanel);
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private JPanel createParentPanel() {
        // 1. Create the parent panel
        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(Color.LIGHT_GRAY);
        contentPanel.setLayout(new BorderLayout());
        // 2. Define the insets/padding (Top, Left, Bottom, Right) in pixels
        int top = 10, left = 10, bottom = 10, right = 10;
        contentPanel.setBorder(BorderFactory.createEmptyBorder(top, left, bottom, right));
        return contentPanel;
    }

    void main() {
        SwingUtilities.invokeLater(() -> {
            PlotExample app = new PlotExample();
            app.setVisible(true);
        });
    }
}

