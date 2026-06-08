package dataandplot.data.range;

public class UpdatableDataBounds {

    private double minX;
    private double maxX;
    private double minY;
    private double maxY;

    public UpdatableDataBounds() {
        reset();
    }

    public void reset() {
        minX = Double.MAX_VALUE;
        minY = Double.MAX_VALUE;
        maxX = Double.MIN_VALUE;
        maxY = Double.MIN_VALUE;
    }

    public DataBounds getDataBounds() {
        return new DataBounds(minX, maxX, minY, maxY);
    }

    public void updateRange(double x, double y) {
        if (x < minX) minX = x;
        if (x > maxX) maxX = x;
        if (y < minY) minY = y;
        if (y > maxY) maxY = y;
    }

    public void updateRange(DataBounds range) {
        if (range.minX() < minX) minX = range.minX();
        if (range.maxX() > maxX) maxX = range.maxX();
        if (range.minY() < minY) minY = range.minY();
        if (range.maxY() > maxY) maxY = range.maxY();
    }

    public String toString() {
        return "(" + minX + ", " + minY + ")(" + maxX + ", " + maxY + ")  w=" +
                (maxX-minX) + "   h=" + (maxY-minY);
    }

}
