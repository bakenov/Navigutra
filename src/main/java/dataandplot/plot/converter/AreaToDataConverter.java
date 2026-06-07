package dataandplot.plot.converter;

import dataandplot.model.data.range.MinMaxDouble;
import dataandplot.model.data.range.RangeType;

public interface AreaToDataConverter extends DataToPixelConverter {
    double[] pixelToPhysical(float px, float py);
    boolean canConvert();
    void onDataRangeChanged(final RangeType rangeType, final MinMaxDouble range);
    boolean isReady();


}
