package dataandplot.plot.converter;

import dataandplot.data.holder.FloatDataPoint;
import dataandplot.plot.Insets;
import dataandplot.util.DataRangeFloat;

import java.awt.geom.Rectangle2D;

public interface PixelConverter {

    void setPlotBounds(Rectangle2D plotBounds);
    DataRangeFloat getPhysicalRange();
    DataRangeFloat getPixelRange();
    int physicalToPixelX(double x);
    int physicalToPixelY(double y);
    FloatDataPoint pixelToPhysical(int px, int py);
}
