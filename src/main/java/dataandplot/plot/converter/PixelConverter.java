package dataandplot.plot.converter;

import dataandplot.model.data.range.MinMaxDouble;

import java.awt.geom.Rectangle2D;

public interface PixelConverter extends DataToPixelConverter {
    void setPlotBounds(Rectangle2D plotBounds);
    void setPhysicalDataRange(final MinMaxDouble range);
    MinMaxDouble getPhysicalRange();
    MinMaxDouble getPixelRange();
    float[] pixelToPhysical(int px, int py);
}
