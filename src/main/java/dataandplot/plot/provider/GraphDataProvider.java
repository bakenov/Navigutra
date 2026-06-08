package dataandplot.plot.provider;

import dataandplot.data.range.RangeChangeListener;
import dataandplot.plot.converter.AreaToDataConverter;

import java.awt.geom.Path2D;

public interface GraphDataProvider extends RangeChangeListener {
    void clear();
    boolean isReady();
    AreaToDataConverter getAreaToDataConverter();
    Path2D.Float getDataPathInPixels(String pathName);
}
