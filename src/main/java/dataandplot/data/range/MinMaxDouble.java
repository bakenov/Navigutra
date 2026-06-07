package dataandplot.data.range;

import java.awt.geom.Rectangle2D;

public record MinMaxDouble(double minX, double maxX, double minY, double maxY) implements MinMax {
    public double width() {
        return maxX - minX;
    }
    public double height() {
        return maxY - minY;
    }
    public MinMaxFloat toFloat() {
        return new MinMaxFloat((float) minX, (float) maxX, (float) minY, (float) maxX);
    }
    public boolean contains(double x, double y) {
        return x >= minX && x <= maxX && y >= minY && y <= maxY;
    }
    public boolean isLineOrPoint() {
        return width() == 0.0 || height() == 0.0;
    }

    public static MinMaxDouble of(double minX, double maxX, double minY, double maxY) {
        return new MinMaxDouble(minX, maxX, minY, maxY);
    }

    public static MinMaxDouble of(double w, double h) {
        return new MinMaxDouble(0, w, 0, h);
    }

    public static MinMaxDouble of(double v) {
        return new MinMaxDouble(-v, v, -v, v);
    }

    public static MinMaxDouble of(Rectangle2D bounds) {
        return new MinMaxDouble(bounds.getMinX(), bounds.getMinX() + bounds.getWidth(),
                bounds.getMinY(), bounds.getMinY() + bounds.getHeight());
    }
}
