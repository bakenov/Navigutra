package dataandplot.plot;

public record Insets(int left, int right, int top, int bottom) {

    public Insets() {
        this(50, 20, 20, 40);
    }

    public int getWidth() {
        return left + right;
    }

    public int getHeight() {
        return top + bottom;
    }
}
