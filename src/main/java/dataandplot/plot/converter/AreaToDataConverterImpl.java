package dataandplot.plot.converter;

import dataandplot.model.data.range.MinMaxDouble;
import dataandplot.model.data.range.RangeType;

import java.util.Arrays;

public class AreaToDataConverterImpl implements AreaToDataConverter {

    private static final int X = 0;
    private static final int Y = 1;

    private MinMaxDouble physicalRange;
    private MinMaxDouble areaRange;
    private double scaleFactorX;
    private double scaleFactorY;

    @Override
    public void onDataRangeChanged(final RangeType rangeType, final MinMaxDouble range) {
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
            IO.println("AreaToDataConverterImpl.onDataRangeChanged()   scales setup  " + this);
        }
    }

    @Override
    public boolean isReady() {
        return physicalRange != null && areaRange != null;
    }

    private void processAreaRangeChange(final MinMaxDouble range) {
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
        return (float) (areaRange.minX() + scaleXToPixel(x));
    }

    @Override
    public float physicalToPixelY(double y) {
        return (float) (areaRange.minY() + scaleYToPixel(y));
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
        float[] retVal =  new float[] {
                (float) Math.round(areaRange.minX() + (point[X] - physicalRange.minX()) * scaleFactorX),
                (float) Math.round(areaRange.height() + areaRange.minY() - (point[Y] - physicalRange.minY()) * scaleFactorY)
        };
//        return new float[] {physicalToPixelX(point[X]), physicalToPixelY(point[Y])};
        return retVal;
    }

    public float[] scalePhysicalToPixel(double[] point) {
        return new float[] {(float) scaleXToPixel(point[X]), (float) scaleYToPixel(point[Y])};
    }

    public String toString() {
        return "AreaToDataConverter(" + physicalRange + "|" + areaRange + "|" + scaleFactorX + "|" + scaleFactorY + ")";
    }
}
