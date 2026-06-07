package dataandplot.plot.converter;

import dataandplot.model.data.range.MinMaxDouble;
import dataandplot.plot.Insets;

import java.awt.geom.Rectangle2D;

public class PixelConverterImpl implements PixelConverter {

    private final Insets plotInsets;
    private MinMaxDouble physicalRange;
    private MinMaxDouble pixelRange;
    // cached values
    private double plotWidth;
    private double plotHeight;

    public PixelConverterImpl(final Insets plotInsets) {
        this.plotInsets = plotInsets;
    }

    public void setPhysicalDataRange(final MinMaxDouble range) {
        IO.println("PixelConverterImpl.setPhysicalDataRange()   " + range);
        this.physicalRange = range;
        if (physicalRange == null) {

        }
    }

    @Override
   public void setPlotBounds(Rectangle2D plotBounds) {
        IO.println("PixelConverterImpl.setPlotBounds  new Bounds: " + plotBounds);
        plotWidth = plotBounds.getWidth() - plotInsets.getWidth();
        plotHeight = plotBounds.getHeight() - plotInsets.getHeight();
        if (physicalRange != null) {
            updatePixelRange();
        }
    }

    // Convert Data to Pixels (for drawing)

    @Override
    public float physicalToPixelX(double x) {
        return (float) Math.round(plotInsets.left() + ((x - physicalRange.minX()) / physicalRange.width()) * plotWidth);
    }

    @Override
    public float physicalToPixelY(double y) {
        return (float) Math.round(plotHeight - plotInsets.bottom() - ((y - physicalRange.minY()) / physicalRange.height()) * plotHeight);
    }

    @Override
    public float[] scalePhysicalToPixel(double[] point) {
        return new float[0];
    }

    @Override
    public float[] physicalToPixel(double[] point) {
        return new float[0];
    }

    // Convert Pixels to Data (for mouse tracking/selection)
    @Override
    public float[] pixelToPhysical(int px, int py) {
        float x = (float) (physicalRange.minX() + ((px - plotInsets.left()) / plotWidth) * physicalRange.width());
        float y = (float) (physicalRange.minY() + ((plotHeight - py - plotInsets.bottom()) / plotHeight) * physicalRange.height());
        return new float[]{x, y};
    }

    private void updatePixelRange() {
        int minX = (int) physicalToPixelX(physicalRange.minX());
        int maxX = (int) physicalToPixelX(physicalRange.maxX());
        int minY = (int) physicalToPixelY(physicalRange.minY());
        int maxY = (int) physicalToPixelY(physicalRange.maxY());
        pixelRange = new MinMaxDouble(minX, minY, maxX, maxY);
    }

    public MinMaxDouble getPixelRange() {
        return pixelRange;
    }

    @Override
    public MinMaxDouble getPhysicalRange() {
        return physicalRange;
    }

}
