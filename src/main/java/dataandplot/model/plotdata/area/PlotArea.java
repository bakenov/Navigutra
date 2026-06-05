package dataandplot.model.plotdata.area;

import dataandplot.model.data.range.MinMaxDouble;
import dataandplot.model.data.range.MinMaxFloat;

// physical values area in doubles
// pixel's area in floats
public class PlotArea {

    private final MinMaxDouble physicalRange;

    private MinMaxFloat pixelRange;

    public PlotArea(final MinMaxDouble physicalRange) {
        this.physicalRange = physicalRange;
    }

    public void setPixelRange(MinMaxFloat pixelRange) {
        this.pixelRange = pixelRange;
    }


}
