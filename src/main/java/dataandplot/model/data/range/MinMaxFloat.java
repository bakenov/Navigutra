package dataandplot.model.data.range;

public record MinMaxFloat(float minX, float maxX, float minY, float maxY) {
    private float width() {
        return maxX - minX;
    }
    private float helght() {
        return maxY - minY;
    }
}