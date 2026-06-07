package dataandplot.data.range;

public class UpdatableRangeFloat {

    private float minX;
    private float maxX;
    private float minY;
    private float maxY;

    public UpdatableRangeFloat() {
        minX = Float.MAX_VALUE;
        minY = Float.MAX_VALUE;
        maxX = Float.MIN_VALUE;
        maxY = Float.MIN_VALUE;
    }

    public MinMaxFloat getMinMaxFloat() {
        return new MinMaxFloat(minX, maxX, minY, maxY);
    }

    public void updateRange(float x, float y) {
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
