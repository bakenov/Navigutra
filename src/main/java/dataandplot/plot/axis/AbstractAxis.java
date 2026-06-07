package dataandplot.plot.axis;

import dataandplot.model.data.range.MinMaxDouble;
import dataandplot.model.data.range.MinMaxFloat;
import dataandplot.plot.converter.PixelConverter;

import java.awt.geom.Line2D;

public class AbstractAxis {

    MinMaxFloat physicalRange;
    MinMaxFloat pixelRange;
    final PixelConverter converter;
    Line2D.Float line;

    public AbstractAxis(final PixelConverter converter) {
        this.converter = converter;
    }

    public void setRanges() {
        this.physicalRange = converter.getPhysicalRange().toFloat();
        this.pixelRange = converter.getPixelRange().toFloat();
    }

    public void updateAxis() {

    }
}
