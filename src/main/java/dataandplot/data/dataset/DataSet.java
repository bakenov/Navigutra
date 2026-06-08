package dataandplot.data.dataset;

import dataandplot.data.range.DataBounds;
import dataandplot.plot.converter.DataToPixelConverter;

import java.awt.geom.Path2D;

public interface DataSet {
    void endOfData();
    int getDataLength();
    DataBounds getDataRange();
    void populatePath(DataToPixelConverter dataToPixelConverter, Path2D.Float convertedPath);
    void setData(double x, double y);
    double[] getDataAt(int index);

}
