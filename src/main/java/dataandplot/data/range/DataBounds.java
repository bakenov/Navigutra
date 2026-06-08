package dataandplot.data.range;

import java.awt.geom.Rectangle2D;

public record DataBounds(double minX, double maxX, double minY, double maxY) {
    public double width() {
        return maxX - minX;
    }
    public double height() {
        return maxY - minY;
    }
    public boolean contains(double x, double y) {
        return x >= minX && x <= maxX && y >= minY && y <= maxY;
    }
    public boolean isLineOrPoint() {
        return width() == 0.0 || height() == 0.0;
    }

    public static DataBounds of(double minX, double maxX, double minY, double maxY) {
        return new DataBounds(minX, maxX, minY, maxY);
    }
    public static DataBounds of(double w, double h) {
        return new DataBounds(0, w, 0, h);
    }
    public static DataBounds of(double v) {
        return new DataBounds(-v, v, -v, v);
    }
    public static DataBounds of(Rectangle2D bounds) {
        return new DataBounds(bounds.getMinX(), bounds.getMinX() + bounds.getWidth(),
                bounds.getMinY(), bounds.getMinY() + bounds.getHeight());
    }
}
