package dataandplot.data.range;

public interface RangeChangeListener {
    void onDataRangeChanged(final RangeType rangeType, final MinMaxDouble range);
}
