package dataandplot.model.data;

import dataandplot.model.data.range.MinMaxDouble;
import dataandplot.model.data.range.UpdatableRangeDouble;
import dataandplot.plot.converter.PixelConverter;

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
    public void populatePath(PixelConverter dataToPixelConverter, Path2D.Float convertedPath) {
        for (int i = 0; i < data.length; i++) {
            double[] point = data[i];
            float x = dataToPixelConverter.physicalToPixelX(point[0]);
            float y = dataToPixelConverter.physicalToPixelY(point[0]);
            if (i == 0)
                convertedPath.moveTo(x, y);
            else
                convertedPath.lineTo(x, y);
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
