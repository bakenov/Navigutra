package dataandplot.model.plotdata.area;

import dataandplot.config.DataConfig;
import dataandplot.model.data.range.MinMaxDouble;

import java.awt.*;

public interface PaintArea {
    void paintComponent(Graphics2D g2);
    void updateAreaRange(final MinMaxDouble range);
    void setDataConfig(final DataConfig dataConfig);
}
