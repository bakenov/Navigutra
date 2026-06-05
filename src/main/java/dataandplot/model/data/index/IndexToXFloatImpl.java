package dataandplot.model.data.index;

public class IndexToXFloatImpl implements IndexToXFloat {

    private final float xunit;

    public IndexToXFloatImpl(float xunit) {
        this.xunit = xunit;
    }

    @Override
    public float toXbyIndex(int index) {
        return index * xunit;
    }
}
