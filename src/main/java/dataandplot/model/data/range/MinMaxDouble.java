package dataandplot.model.data.range;

public record MinMaxDouble(double minX, double maxX, double minY, double maxY) {
        public double width() {
            return maxX - minX;
        }
        public double height() {
            return maxY - minY;
        }
    }
