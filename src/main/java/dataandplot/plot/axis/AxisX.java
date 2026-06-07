package dataandplot.plot.axis;

import dataandplot.plot.converter.PixelConverter;
import dataandplot.util.Utils;

import java.awt.*;
import java.awt.geom.Line2D;

public class AxisX extends AbstractAxis {

    private final int distanceBetweenTicksInPixels = 40;

    public AxisX(final PixelConverter converter) {
        super(converter);
    }

    private void buildLine() {
        line = new Line2D.Float(pixelRange.minX(), pixelRange.minY(), pixelRange.maxX(), pixelRange.minY());
    }

    public void paintAxis(Graphics2D g2) {
        buildLine();
        // Draw Axes
        g2.draw(line);
        paintTicks(g2);
    }

    public void paintTicks(Graphics2D g2) {
        int numberTicks = (int) pixelRange.width() / distanceBetweenTicksInPixels;
        float pixelsBetweenTicks = pixelRange.width() / numberTicks;
        float physicalValueBetweenTicks = physicalRange.width() / numberTicks;
        for (int i = 0; i < numberTicks + 1; i++) {
            float x = pixelRange.minX() + i * pixelsBetweenTicks;
            Line2D.Float tick = new Line2D.Float(x,
                    pixelRange.minY(), x, pixelRange.minY() + 5);
//            IO.println("AxisX.paintTicks()    tick: (" + tick.x1 + ", " + tick.y1 + " : " + tick.x2 + ", " + tick.y2 + ")     i=" + i);

            g2.draw(tick);

            if (i % 2 != 0) {
                float value = physicalValueBetweenTicks * i + physicalRange.minX();
                g2.drawString(Utils.formatNum(value), x - 10, pixelRange.minY() + 20);
            }
        }
    }
}
