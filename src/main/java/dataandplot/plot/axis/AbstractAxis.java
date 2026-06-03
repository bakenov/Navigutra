package dataandplot.plot.axis;

import dataandplot.plot.converter.PixelConverter;
import dataandplot.util.DataRangeFloat;

import java.awt.geom.Line2D;

public class AbstractAxis {

    final DataRangeFloat physicalRange;
    final DataRangeFloat pixelRange;
    final PixelConverter converter;
    Line2D.Float line;

    public AbstractAxis(final PixelConverter converter) {
        this.converter = converter;
        this.physicalRange = converter.getPhysicalRange();
        this.pixelRange = converter.getPixelRange();
    }

    public void updateAxis() {

    }
}
