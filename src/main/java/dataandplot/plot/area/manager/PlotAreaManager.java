package dataandplot.plot.area.manager;

import dataandplot.data.range.RangeChangeListener;

import java.awt.*;

public interface PlotAreaManager extends RangeChangeListener {
    void paintPlot(Graphics2D g2);
}
