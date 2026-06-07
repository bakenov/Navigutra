package dataandplot.plot;

import dataandplot.model.adapter.DataPixelAdapter;
import dataandplot.model.data.range.MinMaxDouble;
import dataandplot.model.data.range.RangeChangeListener;
import dataandplot.model.data.range.RangeType;
import dataandplot.model.plotdata.area.PlotAreaManager;
import dataandplot.plot.axis.AxisX;
import dataandplot.plot.axis.AxisY;
import dataandplot.plot.converter.AreaToDataConverter;
import dataandplot.plot.converter.PixelConverter;
import dataandplot.plot.provider.GraphDataProvider;

import static dataandplot.util.Utils.formatNum;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class PlotPanel extends JPanel {

    private final List<RangeChangeListener> listeners = new ArrayList<>();
    private final PlotAreaManager areaManager;

    public PlotPanel(final PlotAreaManager areaManager, final AreaToDataConverter areaToDataConverter) {
        this.areaManager = areaManager;

        setBackground(Color.WHITE);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                double[] data = areaToDataConverter.pixelToPhysical(e.getX(), e.getY());
                    IO.println("PlotPanel  click on Pixel: [" + e.getX() + ", " + e.getY() + "] --> [" +
                            formatNum(data[0]) + ", " + formatNum(data[1]) + "] --> [");// +
//                            dataToPixelConverter.physicalToPixelX(data[0]) + ", " +
//                            dataToPixelConverter.physicalToPixelY(data[1]) + "]");
            }
        });
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
            Rectangle2D area = e.getComponent().getBounds();
            IO.println("PlotPanel.componentResized()  bounds:" + area);
            IO.println("PlotPanel.componentResized()  MinMaxDouble:" + MinMaxDouble.of(area));
            listeners.forEach(l -> l.onDataRangeChanged(RangeType.PIXEL_RANGE, MinMaxDouble.of(area)));
            }
        });
    }

    public void addDataRangeChangeListener(RangeChangeListener listener) {
        listeners.add(listener);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        areaManager.paintPlot((Graphics2D) g);
    }

    public void setDisplayData(boolean displayData) {
        if (displayData) {
//            axisX.setRanges();
//            axisY.setRanges();
        }
    }

}

