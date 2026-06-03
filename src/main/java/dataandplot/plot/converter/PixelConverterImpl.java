package dataandplot.plot.converter;

import dataandplot.data.holder.FloatDataPoint;
import dataandplot.plot.Insets;
import dataandplot.util.DataRangeFloat;

import java.awt.geom.Rectangle2D;

public class PixelConverterImpl implements PixelConverter {

    private Rectangle2D plotBounds;
    private Insets boundary;
    private final DataRangeFloat physicalRange;
    private final DataRangeFloat pixelRange;
    // cached values
    private double plotWidth;
    private double plotHeight;

    public PixelConverterImpl(final DataRangeFloat range) {
        this.physicalRange = range;
        pixelRange = new DataRangeFloat();
    }

    @Override
    public DataRangeFloat getPhysicalRange() {
        return physicalRange;
    }

    @Override
    public void setInsets(Insets boundary) {
        this.boundary = boundary;
    }

    @Override
   public void setPlotBounds(Rectangle2D plotBounds) {
        this.plotBounds = plotBounds;
        plotWidth = plotBounds.getWidth() - boundary.getWidth();
        plotHeight = plotBounds.getHeight() - boundary.getHeight();
        updatePixelRange();
    }

    // Convert Data to Pixels (for drawing)
    @Override
    public int physicalToPixelX(double x) {
        return (int) Math.round(boundary.left() + ((x - physicalRange.getMinX()) / physicalRange.getRangeX()) * plotWidth);
    }

    @Override
    public int physicalToPixelY(double y) {
        return (int) Math.round(plotBounds.getHeight() - boundary.bottom() - ((y - physicalRange.getMinY()) / physicalRange.getRangeY()) * plotHeight);
    }

    // Convert Pixels to Data (for mouse tracking/selection)
    @Override
    public FloatDataPoint pixelToPhysical(int px, int py) {
        float x = (float) (physicalRange.getMinX() + ((px - boundary.left()) / plotWidth) * physicalRange.getRangeX());
        float y = (float) (physicalRange.getMinY() + ((plotBounds.getHeight() - py - boundary.bottom()) / plotHeight) * physicalRange.getRangeY());
        return new FloatDataPoint(x, y);
    }

    private void updatePixelRange() {
        int minX = physicalToPixelX(physicalRange.getMinX());
        int maxX = physicalToPixelX(physicalRange.getMaxX());
        int minY = physicalToPixelY(physicalRange.getMinY());
        int maxY = physicalToPixelY(physicalRange.getMaxY());
        pixelRange.setRange(minX, minY, maxX, maxY);
    }

    public DataRangeFloat getPixelRange() {
        return pixelRange;
    }

}
