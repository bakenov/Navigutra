package dataandplot.model.data;

import dataandplot.model.data.range.MinMaxDouble;
import dataandplot.model.data.range.UpdatableRangeDouble;

public abstract class AbstractDataSet implements DataSet {
    int dataIndex;
    final UpdatableRangeDouble updatableRange;
    MinMaxDouble minMaxDouble;

    public AbstractDataSet() {
        updatableRange = new UpdatableRangeDouble();
        dataIndex = -1;
    }

    @Override
    public void endOfData() {
        minMaxDouble = updatableRange.getMinMaxDouble();
    }

    @Override
    public MinMaxDouble getDataRange() {
        return minMaxDouble;
    }


}
