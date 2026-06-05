package dataandplot.model.data.index;

public record IndexToXDoubleImpl(double xunit) implements IndexToXDouble {

    @Override
    public double toXbyIndex(int index) {
        return index * xunit;
    }

}
