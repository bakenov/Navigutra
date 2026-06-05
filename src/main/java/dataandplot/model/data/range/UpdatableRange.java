package dataandplot.model.data.range;

public class UpdatableRange {

    private double minX;
    private double maxX;
    private double minY;
    private double maxY;

    public UpdatableRange() {
        minX = Float.MAX_VALUE;
        minY = Float.MAX_VALUE;
        maxX = Float.MIN_VALUE;
        maxY = Float.MIN_VALUE;
    }

    public MinMaxDouble getMinMaxDouble() {
        return new MinMaxDouble(minX, maxX, minY, maxY);
    }

    public void updateRange(double x, double y) {
        if (x < minX) minX = x;
        if (x > maxX) maxX = x;
        if (y < minY) minY = y;
        if (y > maxY) maxY = y;
    }

    public String toString() {
        return "(" + minX + ", " + minY + ")(" + maxX + ", " + maxY + ")  w=" +
                (maxX-minX) + "   h=" + (maxY-minY);
    }

}
