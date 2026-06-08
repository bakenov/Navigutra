package dataandplot.plot.area;

import dataandplot.data.range.DataBounds;
import dataandplot.plot.Insets;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public abstract class AbstractArea implements PaintArea {

    final Insets plotInsets;
    DataBounds areaRange;
    final PaintAreaType type;
    Rectangle2D.Double rect;

    public AbstractArea(Insets plotInsets, final PaintAreaType type) {
        this.plotInsets = plotInsets;
        this.type = type;
    }

    Rectangle2D.Double getComponentRectangle() {
        return rect;
    }

    void updateComponentRectangle() {
        rect = new Rectangle2D.Double(areaRange.minX(), areaRange.minY(), areaRange.width(), areaRange.height());
//        IO.println("AbstractArea.updateComponentRectangle(" + type + ")   areaRange: " + areaRange);
//        IO.println("AbstractArea.updateComponentRectangle(" + type + ")        rect: " + rect);
    }

    void paintBorder(Graphics2D g2) {
//        IO.println("AbstractArea.paintBorder(" + type + ")   areaRange: " + areaRange);
        g2.setColor(getComponentColour());
        g2.fill(getComponentRectangle());
    }

    abstract Color getComponentColour();
}
