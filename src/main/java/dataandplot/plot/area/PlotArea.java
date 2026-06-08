package dataandplot.plot.area;

import dataandplot.config.DataConfig;
import dataandplot.config.DataLineConfig;
import dataandplot.data.range.DataBounds;
import dataandplot.data.range.RangeType;
import dataandplot.plot.Insets;
import dataandplot.plot.provider.GraphDataProvider;

import java.awt.*;
import java.awt.geom.Path2D;
import java.util.List;

public class PlotArea extends AbstractArea {

    private final GraphDataProvider graphDataProvider;
    private DataConfig dataConfig;

    public PlotArea(final Insets plotInsets, final GraphDataProvider graphDataProvider) {
        super(plotInsets, PaintAreaType.PLOT_AREA);
        this.graphDataProvider = graphDataProvider;
    }

    @Override
    public void paintComponent(Graphics2D g2) {
        paintBorder(g2);
        if (dataConfig != null) {
            g2.setColor(Color.BLACK);
            List<DataLineConfig> dataLineConfigs = dataConfig.getDataLineConfigs();
            dataLineConfigs.forEach(dlc -> {
                Path2D.Float path = graphDataProvider.getDataPathInPixels(dlc.name());
                g2.draw(path);
            });
        }

////        Path2D.Float convertedPath = dataProvider.getDataPathInPixels();
////        g2.draw(convertedPath);
    }

    @Override
    public void updateAreaRange(DataBounds panelRange) {
        int xMin = plotInsets.left();
        int xMax = (int) panelRange.width() - plotInsets.right();
        int yMin = plotInsets.top();
        int yMax = (int) panelRange.height() - plotInsets.top() - plotInsets.bottom();
        areaRange = new DataBounds(xMin, xMax, yMin,yMax);
        IO.println("PlotArea.updateAreaBounds()   areaRange: " + areaRange);
        graphDataProvider.onDataRangeChanged(RangeType.PIXEL_RANGE, areaRange);
        updateComponentRectangle();
    }

    @Override
    public void setDataConfig(DataConfig dataConfig) {
        this.dataConfig = dataConfig;
    }

    Color getComponentColour() {
        return Color.GREEN;
    }

}
