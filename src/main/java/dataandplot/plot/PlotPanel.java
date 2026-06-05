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

    private final DataProvider dataProvider;
    private final Insets plotInsets;

    private DataRangeFloat range;
    private PixelConverter converter;
    private AxisX axisX;
    private AxisY axisY;
    private volatile boolean displayData;

    public PlotPanel(final Insets plotInsets, final DataProvider dataProvider) {
        this.plotInsets = plotInsets;
        this.dataProvider = dataProvider;

        setBackground(Color.WHITE);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (displayData) {
                    FloatDataPoint data = converter.pixelToPhysical(e.getX(), e.getY());
                    converter.physicalToPixelX(data.x());
                    IO.println("PlotPanel  click on Pixel: [" + e.getX() + ", " + e.getY() + "] --> [" +
                            formatNum(data.x()) + ", " + formatNum(data.y()) + "] --> [" +
                            converter.physicalToPixelX(data.x()) + ", " +
                            converter.physicalToPixelY(data.y()) + "]");
                }
            }
        });
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                if (displayData) {
                    dataProvider.setPlotBounds(e.getComponent().getBounds());
//                IO.println("PlotPanel  new Bounds: " + e.getComponent().getBounds());
                }
            }
        });
    }

    public void showData() {
        displayData = true;
//        this.converter = dataProvider.getDataToPixelConverter();
//        this.range = converter.getPhysicalRange();
        axisX = new AxisX(converter);
        axisY = new AxisY(converter);
//        IO.println("PlotPanel.showData()   range:" + range);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (displayData) {
            paintPlot((Graphics2D) g);
        }
    }

    private void paintPlot(Graphics2D g2) {
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // Draw Axes
        g2.setColor(Color.BLACK);
        axisX.paintAxis(g2);
        axisY.paintAxis(g2);

        Path2D.Float convertedPath = dataProvider.getDataPathInPixels();
        g2.draw(convertedPath);
    }
}

