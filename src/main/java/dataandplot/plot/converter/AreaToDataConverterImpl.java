package dataandplot.plot.converter;

import dataandplot.data.range.DataBounds;
import dataandplot.data.range.RangeType;

import static dataandplot.util.Utils.X;
import static dataandplot.util.Utils.Y;

import java.util.function.DoubleUnaryOperator;

public class AreaToDataConverterImpl implements AreaToDataConverter {

    private DataBounds physicalRange;
    private DataBounds areaRange;
    private double scaleFactorX;
    private double scaleFactorY;

    private DoubleUnaryOperator transformerX;
    private DoubleUnaryOperator transformerY;

    @Override
    public void onDataRangeChanged(final RangeType rangeType, final DataBounds range) {
        if (range.width() == 0.0 || range.height() == 0.0) {
            throw new RuntimeException("width or height of the range is zero.  " + range);
        }
        if (rangeType == RangeType.PHYSICAL_RANGE) {
            physicalRange = range;
        } else if (rangeType == RangeType.PIXEL_RANGE) {
            processAreaRangeChange(range);
        }
        // check for zero width or height
        if (physicalRange != null && areaRange != null) {
            scaleFactorX = areaRange.width() / physicalRange.width();
            scaleFactorY = areaRange.height() / physicalRange.height();
            transformerX = (double x) -> areaRange.minX() + x;
            transformerY = (double y) -> areaRange.height() + areaRange.minY() - y;
            IO.println("AreaToDataConverterImpl.onDataRangeChanged()   scales setup  " + this);
        }
    }

    @Override
    public boolean isReady() {
        return physicalRange != null && areaRange != null;
    }

    private void processAreaRangeChange(final DataBounds range) {
        if (range.minX() < 0.0 || range.minY() < 0.0) {
            throw new RuntimeException("Pixel range is invalid.  " + range);
        }
        areaRange = range;
    }

    @Override
    public boolean canConvert() {
        return physicalRange != null && areaRange != null;
    }

    public double scaleXToPixel(double x) {
        return (x - physicalRange.minX()) * scaleFactorX;
    }

    public double scaleYToPixel(double y) {
        return (y - physicalRange.minY()) * scaleFactorY;
    }

    @Override
    public float physicalToPixelX(double x) {
        return (float) Math.round(transformerX.applyAsDouble(scaleXToPixel(x)));
    }

    @Override
    public float physicalToPixelY(double y) {
        return (float) Math.round(transformerY.applyAsDouble(scaleYToPixel(y)));
    }

    // Convert Pixels to Data
    @Override
    public double[] pixelToPhysical(float px, float py) {
        return new double[] {
                physicalRange.minX() + (px - areaRange.minX()) / scaleFactorX,
                physicalRange.minY() + (py - areaRange.minY()) / scaleFactorY
        };
    }

    public float[] physicalToPixel(double[] point) {
        return new float[] { physicalToPixelX(point[X]), physicalToPixelY(point[Y]) };
    }

    public float[] scalePhysicalToPixel(double[] point) {
        return new float[] {(float) scaleXToPixel(point[X]), (float) scaleYToPixel(point[Y])};
    }

    public String toString() {
        return "AreaToDataConverter(" + physicalRange + "|" + areaRange + "|" + scaleFactorX + "|" + scaleFactorY + ")";
    }
}
