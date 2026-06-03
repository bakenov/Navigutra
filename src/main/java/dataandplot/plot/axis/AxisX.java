package dataandplot.plot.axis;

import dataandplot.plot.converter.PixelConverter;
import dataandplot.util.Utils;

import java.awt.*;
import java.awt.geom.Line2D;

public class AxisX extends AbstractAxis {

    private int distanceBetweenTicksInPixels = 40;

    public AxisX(final PixelConverter converter) {
        super(converter);
    }

    private void buildLine() {
        line = new Line2D.Float(pixelRange.getMinX(), pixelRange.getMinY(), pixelRange.getMaxX(), pixelRange.getMinY());
    }

    public void paintAxis(Graphics2D g2) {
        buildLine();
        // Draw Axes
        g2.draw(line);
        paintTicks(g2);
    }

    public void paintTicks(Graphics2D g2) {
        int numberTicks = (int) pixelRange.getRangeX() / distanceBetweenTicksInPixels;
        float pixelsBetweenTicks = (float) (pixelRange.getRangeX() / numberTicks);
        double physicalValueBetweenTicks = physicalRange.getRangeX() / numberTicks;
        for (int i = 0; i < numberTicks + 1; i++) {
            float x = pixelRange.getMinX() + i * pixelsBetweenTicks;
            Line2D.Float tick = new Line2D.Float(x,
                    pixelRange.getMinY(), x, pixelRange.getMinY() + 5);
//            IO.println("AxisX.paintTicks()    tick: (" + tick.x1 + ", " + tick.y1 + " : " + tick.x2 + ", " + tick.y2 + ")     i=" + i);

            g2.draw(tick);

            if (i % 2 != 0) {
                double value = physicalValueBetweenTicks * i + physicalRange.getMinX();
                g2.drawString(Utils.formatNum(value), x - 10, pixelRange.getMinY() + 20);
            }
        }
    }
}
