package dataandplot.plot.axis;

import dataandplot.plot.converter.PixelConverter;
import dataandplot.util.Utils;

import java.awt.*;
import java.awt.geom.Line2D;

public class AxisY extends AbstractAxis {

    private int distanceBetweenTicksInPixels = 30;

    public AxisY(final PixelConverter converter) {
        super(converter);
    }

    void buildLine() {
        line = new Line2D.Float(pixelRange.getMinX(), pixelRange.getMinY(), pixelRange.getMinX(), pixelRange.getMaxY());
    }


    public void paintAxis(Graphics2D g2) {
        buildLine();
        // Draw Axes
        g2.draw(line);
        paintTicks(g2);
    }

    public void paintTicks(Graphics2D g2) {
        int numberTicks = (int) Math.abs(pixelRange.getRangeY()) / distanceBetweenTicksInPixels;
        float pixelsBetweenTicks = (float) (pixelRange.getRangeY() / numberTicks);
        double physicalValueBetweenTicks = physicalRange.getRangeY() / numberTicks;
        for (int i = 0; i < numberTicks + 1; i++) {
            float y = pixelRange.getMinY() + i * pixelsBetweenTicks;
            Line2D.Float tick = new Line2D.Float(pixelRange.getMinX() - 5,
                    y, pixelRange.getMinX(), y);
//            IO.println("AxisY.paintTicks()    tick: (" + tick.x1 + ", " + tick.y1 + " : " + tick.x2 + ", " + tick.y2 + ")     i=" + i);
            g2.draw(tick);

            if (i % 2 != 0) {
                double value = physicalValueBetweenTicks * i + physicalRange.getMinY();
                g2.drawString(Utils.formatNum(value), pixelRange.getMinX() - 40, y + 5);
            }
        }
    }
}
