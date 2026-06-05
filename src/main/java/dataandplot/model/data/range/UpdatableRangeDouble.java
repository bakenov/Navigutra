package dataandplot.model.data.range;

public class UpdatableRangeDouble {

    private double minX;
    private double maxX;
    private double minY;
    private double maxY;

    public UpdatableRangeDouble() {
        minX = Double.MAX_VALUE;
        minY = Double.MAX_VALUE;
        maxX = Double.MIN_VALUE;
        maxY = Double.MIN_VALUE;
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
