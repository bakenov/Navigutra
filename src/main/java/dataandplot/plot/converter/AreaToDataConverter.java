package dataandplot.plot.converter;

import dataandplot.data.range.DataBounds;
import dataandplot.data.range.RangeType;

public interface AreaToDataConverter extends DataToPixelConverter {
    double[] pixelToPhysical(float px, float py);
    boolean canConvert();
    void onDataRangeChanged(final RangeType rangeType, final DataBounds range);
    boolean isReady();


}
