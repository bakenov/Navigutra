package dataandplot.plot.converter;

import dataandplot.config.ConfigManager;
import dataandplot.data.holder.FloatDataPoint;
import dataandplot.plot.Insets;
import dataandplot.util.DataRangeFloat;

import java.awt.geom.Rectangle2D;

public class PixelConverterImpl implements PixelConverter {

    private Rectangle2D plotBounds;
    private final Insets plotInsets;
    private DataRangeFloat physicalRange;
    private final DataRangeFloat pixelRange;
    // cached values
    private double plotWidth;
    private double plotHeight;

    public PixelConverterImpl(final Insets plotInsets) {
        this.pixelRange = new DataRangeFloat();
        this.plotInsets = plotInsets;
    }

    public void setDataRange(final DataRangeFloat range) {
        this.physicalRange = range;
    }

    @Override
    public DataRangeFloat getPhysicalRange() {
        return physicalRange;
    }

    @Override
   public void setPlotBounds(Rectangle2D plotBounds) {
        this.plotBounds = plotBounds;
        plotWidth = plotBounds.getWidth() - plotInsets.getWidth();
        plotHeight = plotBounds.getHeight() - plotInsets.getHeight();
        updatePixelRange();
    }

    // Convert Data to Pixels (for drawing)
    @Override
    public int physicalToPixelX(double x) {
        return (int) Math.round(plotInsets.left() + ((x - physicalRange.getMinX()) / physicalRange.getRangeX()) * plotWidth);
    }

    @Override
    public int physicalToPixelY(double y) {
        return (int) Math.round(plotBounds.getHeight() - plotInsets.bottom() - ((y - physicalRange.getMinY()) / physicalRange.getRangeY()) * plotHeight);
    }

    // Convert Pixels to Data (for mouse tracking/selection)
    @Override
    public FloatDataPoint pixelToPhysical(int px, int py) {
        float x = (float) (physicalRange.getMinX() + ((px - plotInsets.left()) / plotWidth) * physicalRange.getRangeX());
        float y = (float) (physicalRange.getMinY() + ((plotBounds.getHeight() - py - plotInsets.bottom()) / plotHeight) * physicalRange.getRangeY());
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
