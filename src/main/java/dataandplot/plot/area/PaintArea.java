package dataandplot.plot.area;

import dataandplot.config.DataConfig;
import dataandplot.data.range.MinMaxDouble;

import java.awt.*;

public interface PaintArea {
    void paintComponent(Graphics2D g2);
    void updateAreaRange(final MinMaxDouble range);
    void setDataConfig(final DataConfig dataConfig);
}
