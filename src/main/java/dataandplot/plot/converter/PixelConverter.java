package dataandplot.plot.converter;

import dataandplot.util.DataRangeFloat;

import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;

public interface PixelConverter {

    void setPlotBounds(Rectangle2D plotBounds);
    DataRangeFloat getPhysicalRange();
    DataRangeFloat getPixelRange();
    int physicalToPixelX(double x);
    int physicalToPixelY(double y);
}
