package dataandplot.model.data.index;

public class IndexToXDoubleImpl implements IndexToXDouble {

    private final double xunit;

    public IndexToXDoubleImpl(double xunit) {
        this.xunit = xunit;
    }

    @Override
    public double toXbyIndex(int index) {
        return index * xunit;
    }
}
