package dataandplot.model.plotdata.area;

import dataandplot.model.data.range.RangeChangeListener;

import java.awt.*;

public interface PlotAreaManager extends RangeChangeListener {
    void paintPlot(Graphics2D g2);
}
