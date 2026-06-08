package dataandplot.data.dataset;

import dataandplot.data.range.DataBounds;
import dataandplot.data.range.UpdatableDataBounds;

public abstract class AbstractDataSet implements DataSet {
    int dataIndex;
    final UpdatableDataBounds updatableRange;
    DataBounds minMaxDouble;

    public AbstractDataSet() {
        updatableRange = new UpdatableDataBounds();
        dataIndex = -1;
    }

    @Override
    public void endOfData() {
        minMaxDouble = updatableRange.getDataBounds();
    }

    @Override
    public DataBounds getDataRange() {
        return minMaxDouble;
    }


}
