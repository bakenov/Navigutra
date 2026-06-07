package dataandplot.data.provider;

import dataandplot.data.range.RangeChangeListener;

public interface DataProvider {

    void buildData();
    void addDataRangeChangeListener(RangeChangeListener listener);
}
