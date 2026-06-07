package dataandplot.model.plotdata.area;

import dataandplot.config.DataConfig;
import dataandplot.model.data.range.MinMaxDouble;
import dataandplot.plot.Insets;

import java.awt.*;

public class TitleArea extends AbstractArea {

    private final String title;

    public TitleArea(Insets plotInsets, final String title) {
        super(plotInsets, PaintAreaType.TITLE_AREA);
        this.title = title;
    }

    @Override
    public void paintComponent(Graphics2D g2) {
//        paintBorder(g2);
//        g2.setColor(Color.BLACK);
//        Font font = new Font("Arial", Font.PLAIN, 14);
//        // Get the metrics for the specific font
//        FontMetrics metrics = g2.getFontMetrics(font);
//        // Calculate the width in pixels
//        int widthInPixels = metrics.stringWidth(title);
//        g2.drawString(title, (int) (rect.width - widthInPixels) / 2, 20);
    }

    @Override
    public void updateAreaRange(MinMaxDouble range) {
        IO.println("TitleArea.updateAreaBounds()   range: " + range);
        IO.println("TitleArea.updateAreaBounds()   plotInsets: " + plotInsets);
        int xMin = plotInsets.left();
        int xMax = (int) range.width() - plotInsets.right();
        int yMin = 0;
        int yMax = yMin + plotInsets.top();
        areaRange = new MinMaxDouble(xMin, xMax, yMin,yMax);
        updateComponentRectangle();
    }

    @Override
    public void setDataConfig(DataConfig dataConfig) {

    }

    Color getComponentColour() {
        return Color.WHITE;
    }
}
