package dataandplot.model.plotdata.area;

import dataandplot.config.DataConfig;
import dataandplot.model.data.range.MinMaxDouble;
import dataandplot.plot.Insets;
import dataandplot.plot.converter.AreaToDataConverter;

import java.awt.*;

public class AxisArea extends AbstractArea {

    private final AreaToDataConverter areaToDataConverter;

    public AxisArea(final Insets plotInsets, final PaintAreaType type, final AreaToDataConverter areaToDataConverter) {
        super(plotInsets, type);
        this.areaToDataConverter = areaToDataConverter;
    }

    @Override
    public void paintComponent(Graphics2D g2) {

        //paintBorder(g2);
    }

    @Override
    public void updateAreaRange(MinMaxDouble range) {
        int xMin = 0, xMax = 0, yMin = 0, yMax = 0;
        if (type == PaintAreaType.X_AXIS) {
            xMin = plotInsets.left();
            xMax = (int) range.width() - plotInsets.right();
            yMin = (int) (range.height() - plotInsets.bottom());
            yMax = yMin + plotInsets.bottom();
        } else if (type == PaintAreaType.Y_AXIS) {
            xMin = 0;
            xMax = plotInsets.left();
            yMin = 0;
            yMax = (int) range.height();
        }
        areaRange = new MinMaxDouble(xMin, xMax, yMin, yMax);
        updateComponentRectangle();
    }

    @Override
    public void setDataConfig(DataConfig dataConfig) {

    }

    Color getComponentColour() {
        if (type == PaintAreaType.X_AXIS) {
            return Color.LIGHT_GRAY;
        }
        return Color.DARK_GRAY;
    }


}
