package dataandplot.model.plotdata.area;

import dataandplot.config.ConfigManager;
import dataandplot.config.UIConfig;
import dataandplot.model.data.range.MinMaxDouble;
import dataandplot.model.data.range.MinMaxFloat;
import dataandplot.model.data.range.RangeType;
import dataandplot.plot.Insets;
import dataandplot.plot.provider.GraphDataProvider;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class PlotAreaManagerImpl implements PlotAreaManager {

    private final List<PaintArea> components = new ArrayList<>();
    private final ConfigManager configManager;
    private final GraphDataProvider graphDataProvider;

    public PlotAreaManagerImpl(final ConfigManager configManager, final GraphDataProvider graphDataProvider) {
        this.configManager = configManager;
        this.graphDataProvider = graphDataProvider;
        Insets insets = configManager.getUIConfig().plotInsets();
        components.add(new PlotArea(insets, graphDataProvider));
        components.add(new AxisArea(insets, PaintAreaType.X_AXIS, graphDataProvider.getAreaToDataConverter()));
        components.add(new AxisArea(insets, PaintAreaType.Y_AXIS, graphDataProvider.getAreaToDataConverter()));
        //components.add(new TitleArea(insets, config.name()));
    }

    @Override
    public void paintPlot(Graphics2D g2) {
        //g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        components.forEach(c -> c.paintComponent(g2));
    }

    @Override
    public void onDataRangeChanged(RangeType rangeType, MinMaxDouble range) {
        if (rangeType == RangeType.PIXEL_RANGE) {
            components.forEach(c -> c.updateAreaRange(range));
        }
        if (graphDataProvider.isReady()) {
            components.forEach(c -> c.setDataConfig(configManager.getDataConfig()));
        }
    }



}
