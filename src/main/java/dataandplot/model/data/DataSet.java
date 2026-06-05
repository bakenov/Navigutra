package dataandplot.model.data;

import dataandplot.model.data.range.MinMaxDouble;
import dataandplot.plot.converter.PixelConverter;

import java.awt.geom.Path2D;
import java.util.Map;

public interface DataSet {
    void endOfData();
    int getDataLength();
    MinMaxDouble getDataRange();
    void populatePath(PixelConverter dataToPixelConverter, Path2D.Float convertedPath);
}
