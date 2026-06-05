package dataandplot.model.adapter;

import dataandplot.model.data.DataSet;
import dataandplot.plot.converter.PixelConverter;

import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;

public interface DataPixelAdapter {

    PixelConverter getDataToPixelConverter();
    Path2D.Float getDataPathInPixels();
    void setPlotBounds(Rectangle2D plotBounds);
    void allDataGenerated();

}
