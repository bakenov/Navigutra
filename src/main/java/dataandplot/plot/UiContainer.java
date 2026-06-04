package dataandplot.plot;

import dataandplot.config.ConfigManager;
import dataandplot.data.provider.DataProvider;

import javax.swing.*;
import java.awt.*;

public class UiContainer {

    private final ConfigManager configManager;
    private final DataProvider dataProvider;
    private final JFrame frame;

    public UiContainer(final ConfigManager configManager, final DataProvider dataProvider) {
        // 1. set config manager
        this.configManager = configManager;
        this.dataProvider = dataProvider;
        // 2. build frame
        this.frame = new JFrame(configManager.getTitle());
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(configManager.getFrameSize());
        this.frame.setLocationRelativeTo(null);

        // 1. Create the content panel
        JPanel contentPanel = createParentPanel();
        // 2. Create the plot panel
        PlotPanel plotPanel = new PlotPanel(configManager, dataProvider);
        contentPanel.add(plotPanel, BorderLayout.CENTER);
        this.frame.add(contentPanel);
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

    public void showFrame() {
        frame.setVisible(true);
    }
}
