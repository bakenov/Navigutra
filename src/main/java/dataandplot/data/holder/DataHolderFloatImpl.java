package dataandplot.data.holder;

import dataandplot.data.generator.config.GeneratorInfo;
import dataandplot.util.DataRangeFloat;

public class DataHolderFloatImpl implements DataHolderFloat {

    private final FloatDataPoint[] data;
    private final GeneratorInfo generatorInfo;
    private final DataRangeFloat dataRange;
    private int dataIndex;

    public DataHolderFloatImpl(final GeneratorInfo info) {
        generatorInfo = info;
        dataRange = new DataRangeFloat();
        data = new FloatDataPoint[generatorInfo.size()];
        dataIndex = -1;
    }

    public GeneratorInfo getGeneratorInfo() {
        return generatorInfo;
    }

    @Override
    public int getDataLength() {
        return data.length;
    }

    @Override
    public FloatDataPoint getFloatDataPoint(int index) {
        return data[index];
    }

    @Override
    public void setData(float x, float y) {
        dataIndex++;
        data[dataIndex] = new FloatDataPoint(x, y);
        dataRange.updateRange(x, y);
    }

    @Override
    public void closeDataPath() {
        //IO.println("DataHolderFloatImpl.closeDataPath()    assumes data size: " + generatorInfo.size() + "   real size: " + (dataIndex + 1));
    }

    @Override
    public DataRangeFloat getDataRange() {
        return dataRange;
    }

}
