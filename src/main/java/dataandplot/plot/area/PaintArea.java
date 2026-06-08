package dataandplot.plot.area;

import dataandplot.config.DataConfig;
import dataandplot.data.range.DataBounds;

import java.awt.*;

public interface PaintArea {
    void paintComponent(Graphics2D g2);
    void updateAreaRange(final DataBounds range);
    void setDataConfig(final DataConfig dataConfig);
}
