package dataandplot.data.dataset;

import dataandplot.plot.converter.DataToPixelConverter;

import java.awt.geom.Path2D;

public class DataSetDoubleImpl extends AbstractDataSet implements DataSetDouble {

    private final double[][] data;

    public DataSetDoubleImpl(int size) {
        super();
        data = new double[size][2];
    }

    public void setData(double x, double y) {
        dataIndex++;
        if (dataIndex < data.length) {
            data[dataIndex][0] = x;
            data[dataIndex][1] = y;
            updatableRange.updateRange(x, y);
        } else {
            throw new RuntimeException("Should not be here.");
        }
    }

    @Override
    public int getDataLength() {
        return data.length;
    }

    @Override
    public void populatePath(DataToPixelConverter dataToPixelConverter, Path2D.Float convertedPath) {
        for (int i = 0; i < data.length; i++) {
            double[] point = data[i];
            float[] pixelPoint = dataToPixelConverter.physicalToPixel(point);
            if (i == 0)
                convertedPath.moveTo(pixelPoint[0], pixelPoint[1]);
            else
                convertedPath.lineTo(pixelPoint[0], pixelPoint[1]);
        }
    }

    @Override
    public double[] getDataAt(int index) {
        if (dataIndex < data.length) {
            return data[index];
        } else {
            throw new RuntimeException("Should not be here.");
        }
    }
}
