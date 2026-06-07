package dataandplot.plot.provider;

import dataandplot.model.data.range.RangeChangeListener;
import dataandplot.plot.converter.AreaToDataConverter;

import java.awt.geom.Path2D;

public interface GraphDataProvider extends RangeChangeListener {
    boolean isReady();
    AreaToDataConverter getAreaToDataConverter();
    Path2D.Float getDataPathInPixels(String pathName);
}
