package dataandplot.util;

public class DataRangeFloat {

    private float minX;
    private float maxX;
    private float minY;
    private float maxY;

    public DataRangeFloat() {
        minX = Float.MAX_VALUE;
        minY = Float.MAX_VALUE;
        maxX = Float.MIN_VALUE;
        maxY = Float.MIN_VALUE;
    }

    public void setRange(float minX, float minY, float maxX, float maxY) {
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
    }

    public void updateRange(float x, float y) {
        if (x < minX) minX = x;
        if (x > maxX) maxX = x;
        if (y < minY) minY = y;
        if (y > maxY) maxY = y;
    }

    public double getRangeX() {
        return maxX - minX;
    }

    public double getRangeY() {
        return maxY - minY;
    }

    public float getMinX() {
        return minX;
    }

    public float getMinY() {
        return minY;
    }

    public float getMaxX() {
        return maxX;
    }

    public float getMaxY() {
        return maxY;
    }

    public String toString() {
        return "(" + minX + ", " + minY + ")(" + maxX + ", " + maxY + ")  w=" +
                (maxX-minX) + "   h=" + (maxY-minY);
    }
}