package dataandplot.model.data.range;

public interface RangeChangeListener {
    void onDataRangeChanged(final RangeType rangeType, final MinMaxDouble range);
}
