package dataandplot.plot;

import dataandplot.config.ConfigManager;
import dataandplot.config.DataConfig;
import dataandplot.config.UIConfig;
import dataandplot.data.provider.DataProvider;
import dataandplot.plot.area.manager.PlotAreaManager;
import dataandplot.plot.converter.AreaToDataConverter;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class UiContainer {

    public static final String CONFIG_MENU_ITEM_NAME = "Data Config File";
    public static final String BUILD_MENU_ITEM_NAME = "Build Data";
    public static final String BUILD_PLOT_MENU_ITEM_NAME = "Build And Display Data";
    public static final String DISPLAY_DATA_MENU_ITEM_NAME = "Display Data";

    private final ConfigManager configManager;
    private final DataProvider dataProvider;
    private final JFrame frame;
    private final PlotPanel plotPanel;

    public UiContainer(final ConfigManager configManager,
                       final DataProvider dataProvider,
                       final PlotAreaManager areaManager,
                       final AreaToDataConverter areaToDataConverter) {
        // 1. set config manager
        this.configManager = configManager;
        this.dataProvider = dataProvider;
        // 2. get UI config
        UIConfig uiConfig = configManager.getUIConfig();
        // 3. build frame
        this.frame = new JFrame(uiConfig.name());
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(uiConfig.dimension());
        // 4. build Plot panel
        this.plotPanel = new PlotPanel(areaManager, areaToDataConverter);
        // 5. Create the menu
        frame.setJMenuBar(buildMenu());
        // 6. Create the content panel
        JPanel contentPanel = createParentPanel();
        // 7. Add the plot panel
        contentPanel.add(plotPanel, BorderLayout.CENTER);
        frame.add(contentPanel);
    }

    private JMenuBar buildMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        fileMenu.add(buildMenuItem(CONFIG_MENU_ITEM_NAME));
        fileMenu.add(buildMenuItem(BUILD_MENU_ITEM_NAME));
        fileMenu.add(buildMenuItem(BUILD_PLOT_MENU_ITEM_NAME));
        fileMenu.add(buildMenuItem(DISPLAY_DATA_MENU_ITEM_NAME));
        menuBar.add(fileMenu);
        return menuBar;
    }

    private JMenuItem buildMenuItem(String name) {
        JMenuItem menuItem = new JMenuItem(name);
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (name) {
                    case CONFIG_MENU_ITEM_NAME:
                        File file = null;
                        JFileChooser fileChooser = new JFileChooser();
                        // Set default directory to user's home folder (Optional)
                        fileChooser.setCurrentDirectory(configManager.getConfigDir());
                        // Display the dialog frame
                        int response = fileChooser.showOpenDialog(frame);
                        // Verify if the user clicked "Open" instead of canceling
                        if (response == JFileChooser.APPROVE_OPTION) {
                            file = fileChooser.getSelectedFile();
                        }
                        if (file != null) {
                            dataConfigFileChanged(file);
                        }
                        break;
                    case BUILD_MENU_ITEM_NAME:
                        buildData();
                        frame.revalidate();
                        frame.repaint();
                        break;
                    case BUILD_PLOT_MENU_ITEM_NAME:
                        buildData();
                        plotPanel.setDisplayData(true);
                        break;
                    case DISPLAY_DATA_MENU_ITEM_NAME:
                        plotPanel.setDisplayData(true);
                }
            };
        });
        return menuItem;
    }

    private void buildData() {
        dataProvider.buildData();
        //adapter.allDataGenerated();
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
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void dataConfigFileChanged(File newDataConfigFile) {
        if (configManager.dataConfigFileChanged(newDataConfigFile)) {
            // actions on change of data configuration
            DataConfig dataConfig = configManager.getDataConfig();
            frame.setTitle(dataConfig.getTitle());
            plotPanel.setDisplayData(false);
        }
        //dataProvider.setPlotBounds();
    }

    public PlotPanel getPlotPanel() {
        return plotPanel;
    }

}
