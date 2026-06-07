package dataandplot.plot.converter;

public interface DataToPixelConverter {
    float physicalToPixelX(double x);
    float physicalToPixelY(double y);
    float[] scalePhysicalToPixel(double[] point);
    float[] physicalToPixel(double[] point);
}
