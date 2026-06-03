package dataandplot.plot;

import dataandplot.data.holder.FloatDataPoint;
import dataandplot.data.provider.DataProvider;
import dataandplot.plot.axis.AxisX;
import dataandplot.plot.axis.AxisY;
import dataandplot.plot.converter.PixelConverter;
import dataandplot.util.DataRangeFloat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Path2D;

import static dataandplot.util.Utils.formatNum;

public class PlotPanel extends JPanel {
    private final DataRangeFloat range;
    private final Insets boundary = new Insets();
    private final PixelConverter converter;
    private final DataProvider dataProvider;
    private final AxisX axisX;
    private final AxisY axisY;

    public PlotPanel(final DataProvider dataProvider) {
        this.dataProvider = dataProvider;
        this.converter = dataProvider.getDataToPixelConverter();
        this.range = converter.getPhysicalRange();
        axisX = new AxisX(converter);
        axisY = new AxisY(converter);
        IO.println("PlotPanel()   range:" + range);
        converter.setInsets(boundary);
        setBackground(Color.WHITE);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                FloatDataPoint data = converter.pixelToPhysical(e.getX(), e.getY());
                converter.physicalToPixelX(data.x());
                IO.println("PlotPanel  click on Pixel: [" + e.getX() + ", " + e.getY() + "] --> [" +
                        formatNum(data.x()) + ", " + formatNum(data.y()) + "] --> [" +
                        converter.physicalToPixelX(data.x()) + ", " +
                        converter.physicalToPixelY(data.y()) + "]");
            }
        });
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                dataProvider.setPlotBounds(e.getComponent().getBounds());
//                IO.println("PlotPanel  new Bounds: " + e.getComponent().getBounds());
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw Axes
        g2.setColor(Color.BLACK);
        axisX.paintAxis(g2);
        axisY.paintAxis(g2);

        Path2D.Float convertedPath = dataProvider.getDataPathInPixels();
        g2.draw(convertedPath);
    }
}

