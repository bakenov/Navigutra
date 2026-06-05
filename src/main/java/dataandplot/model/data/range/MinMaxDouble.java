package dataandplot.model.data.range;

public record MinMaxDouble(double minX, double maxX, double minY, double maxY) implements MinMax {
        public double width() {
            return maxX - minX;
        }
        public double height() {
            return maxY - minY;
        }
    }
