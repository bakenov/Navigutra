package dataandplot.data.holder;

import static dataandplot.util.Utils.formatNum;

public record FloatDataPoint(float x, float y) {

    public String toString() {
        return "(" + formatNum(x) + ", " + formatNum(y) + ")";
    }
}
