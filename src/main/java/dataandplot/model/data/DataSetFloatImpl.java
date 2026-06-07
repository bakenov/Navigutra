package dataandplot.model.data;

import dataandplot.plot.converter.DataToPixelConverter;
import dataandplot.plot.converter.PixelConverter;

import java.awt.geom.Path2D;

public class DataSetFloatImpl extends AbstractDataSet implements DataSetFloat {

    private final float[][] data;

    public DataSetFloatImpl(int size) {
        super();
        data = new float[size][2];
    }

    @Override
    public void setData(float x, float y) {
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
    public float[] getDataAt(int index) {
        if (dataIndex < data.length) {
            return data[index];
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

    }
}
