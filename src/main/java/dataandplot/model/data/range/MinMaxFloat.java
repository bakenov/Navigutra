package dataandplot.model.data.range;

public record MinMaxFloat(float minX, float maxX, float minY, float maxY) implements MinMax {
    public float width() {
        return maxX - minX;
    }
    public float helght() {
        return maxY - minY;
    }
}