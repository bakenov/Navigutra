package dataandplot.data.provider;

import dataandplot.model.data.range.RangeChangeListener;

public interface DataProvider {

    void buildData();
    void addDataRangeChangeListener(RangeChangeListener listener);
}
